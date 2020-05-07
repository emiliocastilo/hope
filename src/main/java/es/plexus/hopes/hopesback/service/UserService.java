package es.plexus.hopes.hopesback.service;

import static es.plexus.hopes.hopesback.configuration.security.Constants.FIRST_TOKEN_EXPIRATION_TIME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.plexus.hopes.hopesback.configuration.MailTemplateConfiguration;
import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.repository.TokenRepository;
import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Token;
import es.plexus.hopes.hopesback.repository.model.TokenType;
import es.plexus.hopes.hopesback.repository.model.User;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mail.MailService;
import es.plexus.hopes.hopesback.service.mail.TemplateMail;
import es.plexus.hopes.hopesback.service.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class UserService {

	private final UserMapper userMapper;
	private final RoleService roleService;
	private final UserRepository userRepository;
	private final HospitalService hospitalService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final MailService mailService;
	private final TokenRepository tokenRepository;
	private static final String EVERY_30_MINUTES = "0 0/30 * * * ?";
	
	@Autowired
	MailTemplateConfiguration mailTemplateConfiguration;
	
	@Autowired
	public UserService(final UserRepository userRepository, final UserMapper userMapper,
					   final RoleService roleService, final HospitalService hospitalService,
					   final BCryptPasswordEncoder bCryptPasswordEncoder,
					   final MailService mailService,
					   final TokenRepository tokenRepository) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.roleService = roleService;
		this.hospitalService = hospitalService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.mailService = mailService;
		this.tokenRepository = tokenRepository;
	}


	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(userMapper::userToUserDTOConverter).collect(Collectors.toList());
	}

	public UserDTO getOneUserById(final Long id) {
		final Optional<User> user = getOneUserCommon(id);

		UserDTO userDTO = null;

		if (user.isPresent()) {
			userDTO = userMapper.userToUserDTOConverter(user.get());
		}

		return userDTO;
	}

	public UserDTO getOneUserByName(final String name) {
		UserDTO userDTO = null;

		final Optional<User> user = userRepository.findByUsername(name);

		if (user.isPresent()) {
			userDTO = userMapper.userToUserDTOConverter(user.get());
		}

		return userDTO;
	}

	public UserDTO addUser(final UserDTO userDTO) throws ServiceException {
		final User user = addUserAndReturnEntity(userDTO);
		return userMapper.userToUserDTOConverter(user);
	}

	User addUserAndReturnEntity(final UserDTO userDTO) throws ServiceException {
		validateUsername(userDTO);

		final User user = userMapper.userDTOToUserConverter(userDTO);
		user.setHospital(searchHospital(userDTO));
		user.setRoles(roleService.getAllRolesByIdSet(userDTO.getRoles()));
		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

		updateAccountStatus(userDTO, user);

		return userRepository.save(user);
	}

	private Optional<User> getOneUserCommon(Long id) {
		return userRepository.findById(id);
	}

	private void validateUsername(UserDTO userDTO) throws ServiceException {
		if (userDTO != null && userDTO.getId() == null && getOneUserByName(userDTO.getUsername()) != null) {
			throw ServiceExceptionCatalog.USERNAME_DUPLICATE_EXCEPTION.exception(
					String.format("User with name %s already exists", userDTO.getUsername()));
		}
	}

	private Hospital searchHospital(UserDTO userDTO) throws ServiceException {
		final Optional<Hospital> hospital = hospitalService.getOneHospitalById(userDTO.getHospitalId());

		if (!hospital.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
					String.format("Hospital with id %d not found. Hospital is mandatory for the user",
							userDTO.getHospitalId()));
		}

		return hospital.get();
	}

	//ToDo Hasta el 23-04-2020 solo se puede desactivar un usuario por BD
	private void updateAccountStatus(UserDTO userDTO, User user) {
		final Long userId = userDTO.getId();

		if (userId != null) {
			Optional<User> storedUser = getOneUserCommon(userId);
			storedUser.ifPresent(value -> user.setActive(value.isActive()));
		}
	}
	
	public void requestPasswordChange(final String email) throws ServiceException {
        log.debug("requestPasswordChange");

        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            log.debug("User found -> create temporal token");
            
            String token = UUID.randomUUID().toString();
            createPasswordResetTokenForUser(optionalUser.get(), bCryptPasswordEncoder.encode(token));
            
            final TemplateMail simpleMail = TemplateMail.builder()
                    .from(mailTemplateConfiguration.getDefaultSender())
                    .to(optionalUser.get().getEmail())
                    .subject(mailTemplateConfiguration.getDefaultSubject())
                    .text(mailTemplateConfiguration.getDefaultText())
                    .template("emails/changePassword")
                    .templateParam("passwordChangeUrl", mailTemplateConfiguration.getResetPasswordUrl() + token)
                    .html(true)
                    .build();
            mailService.sendMail(simpleMail);
            
        } else {
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
				.exception("Error: Email address not found while requesting password change");
        }
    }
	
	private void createPasswordResetTokenForUser(User user, String token) {
	    Token createPasswordToken = new Token();
	    createPasswordToken.setValue(token);
	    createPasswordToken.setUser(user);
	    createPasswordToken.setType(TokenType.PASSWORD_CHANGE);
	    createPasswordToken.setTokenCreationDate(LocalDateTime.now());
	    createPasswordToken.setTokenExpirationDate(LocalDateTime.now().plusSeconds(FIRST_TOKEN_EXPIRATION_TIME));
	    tokenRepository.save(createPasswordToken);
	}
	
	public Token resetPassword(String token) {		
		return findPasswordResetToken(token);				
	}
	
	public String saveNewPassword(PasswordDTO passwordDTO) throws ServiceException {
		 
	    Token passwordResetToken = findPasswordResetToken(passwordDTO.getToken());
	 
	    if(passwordResetToken == null) {
	    	throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
				.exception("Error: Token for password not found");
	    }	    
	    
	    changeUserPassword(passwordResetToken.getUser(), passwordDTO.getNewPassword());
	    return "OK";
	}

	private void changeUserPassword(User user, String password) {
	    user.setPassword(bCryptPasswordEncoder.encode(password));
	    userRepository.save(user);
	}
	
	private Token findPasswordResetToken(String token) {
		final List<Token> listToken = tokenRepository.findByTypeAndTokenExpirationDateAfter(
				TokenType.PASSWORD_CHANGE,
				LocalDateTime.now());
		return listToken.stream()
				.filter(p -> bCryptPasswordEncoder.matches(token, p.getValue()))
				.findAny()
				.orElse(null);
	}
	
	@Scheduled(cron = EVERY_30_MINUTES)
	@Transactional 
	public void removeTokenExpired() {
		log.info("Delete expired tokens");
        tokenRepository.deleteByTokenExpirationDateBefore(LocalDateTime.now());
	}
	
	public String updatePassword(PasswordDTO passwordDTO) throws ServiceException {
		log.info("Find user in context");
	    Optional<User> user = userRepository.findByUsername(
	    		SecurityContextHolder.getContext().getAuthentication().getName());
	     
	    if (user.isPresent()) { 
	    	if(checkIfValidOldPassword(user.get(), passwordDTO.getPassword())) {
	    		changeUserPassword(user.get(), passwordDTO.getNewPassword());
	    	} else {	
	    		throw ServiceExceptionCatalog.INVALID_LOGIN
					.exception("Error: Invalid password");
	    	}
	    } else {
            throw ServiceExceptionCatalog.INVALID_LOGIN
				.exception("Error: Username not found while requesting password change");
        }
	    
	    return "OK";
	}

	private boolean checkIfValidOldPassword(User user, String password) {
		return bCryptPasswordEncoder.matches(password, user.getPassword());		
	}
	
	
}