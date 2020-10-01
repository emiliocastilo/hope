package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.configuration.MailTemplateConfiguration;
import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.controller.model.UserUpdateDTO;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static es.plexus.hopes.hopesback.configuration.security.Constants.FIRST_TOKEN_EXPIRATION_TIME;


@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final RoleService roleService;
	private final UserRepository userRepository;
	private final HospitalService hospitalService;
	private final PasswordManagementService passwordManagementService;
	private final MailService mailService;
	private final TokenRepository tokenRepository;
	private final MailTemplateConfiguration mailTemplateConfiguration;
	private static final String EVERY_30_MINUTES = "0 0/30 * * * ?";
	private final ServiceService serviceService;
	private final PathologyService pathologyService;


	public Page<UserDTO> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(userMapper::userToUserDTOConverter);
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

	public UserSimpleDTO getOneSimpleUserByUsername(final String username, String role) {
		UserSimpleDTO userSimpleDTO = null;

		final Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent()) {
			userSimpleDTO = userMapper.userToUserSimpleDTOConverter(user.get());
			RoleDTO roleDTO = roleService.findByNameHospitalAndPathology(role);
			if (roleDTO != null) {
				userSimpleDTO.setRolSelected(roleDTO);
			}
		}

		return userSimpleDTO;
	}

	public UserDTO addUser(final UserDTO userDTO) throws ServiceException {
		checkServiceExistence(userDTO);
		User user = addUserAndReturnEntity(userDTO);
		final String password = setGeneratedPasswordForUser(user);
		user = userRepository.save(user);

		sendCredentialsEmail(user, password);

		return userMapper.userToUserDTOConverter(user);
	}

	public String setGeneratedPasswordForUser(final User user) {
		final String password = passwordManagementService.generatePassword();
		user.setPassword(passwordManagementService.encode(password));
		return password;
	}

	User addUserAndReturnEntity(final UserDTO userDTO) throws ServiceException {
		return addUserAndReturnEntityCommon(userDTO);
	}

	User addUserAndReturnEntity(final UserUpdateDTO userUpdateDTO) throws ServiceException {
		UserDTO userDTO = userMapper.userUpdateDTOToUserDTOConverter(userUpdateDTO);
		return addUserAndReturnEntityCommon(userDTO);
	}

	private User addUserAndReturnEntityCommon(UserDTO userDTO) throws ServiceException {
		validateUsername(userDTO);
		validateEmail(userDTO);
		final User user = userMapper.userDTOToUserConverter(userDTO);

		if (userDTO.getHospitalId() != null) {
			user.setHospital(searchHospital(userDTO));
		}

		if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
			user.setRoles(roleService.getAllRolesByIdSet(userDTO.getRoles()));
		}

		if (userDTO.getServiceId() != null){
			Optional<es.plexus.hopes.hopesback.repository.model.Service> service = serviceService.getOneServiceById(userDTO.getServiceId());
			service.ifPresent(user::setService);
		}

		updateAccountStatus(userDTO, user);
		return user;
	}

	private void validateEmail(UserDTO userDTO) {
		if (userDTO != null && userDTO.getId() == null && userRepository.existsByEmail(userDTO.getEmail())) {
			throw ServiceExceptionCatalog.EMAIL_VIOLATION_CONSTRAINT_EXCEPTION.exception(
					String.format("El email %s ya existe", userDTO.getEmail()));
		}
	}

	Optional<User> getOneUserCommon(Long id) {
		return userRepository.findById(id);
	}

	private void validateUsername(UserDTO userDTO) throws ServiceException {
		if (userDTO != null && userDTO.getId() == null && userRepository.existsByUsername(userDTO.getUsername())) {
			throw ServiceExceptionCatalog.USERNAME_DUPLICATE_EXCEPTION.exception(
					String.format("User with name %s already exists", userDTO.getUsername()));
		}
	}

	private Hospital searchHospital(UserDTO userDTO) throws ServiceException {
		final Optional<Hospital> hospital = hospitalService.getOneHospitalById(userDTO.getHospitalId());

		if (!hospital.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
					String.format("Hospital con id %d no encontrado. El hospital es requerido para el usuario.",
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

	public void sendCredentialsEmail(final User user, final String password) throws ServiceException {
		log.debug("sendCredentialsEmail");

		final TemplateMail simpleMail = TemplateMail.builder()
				.from(mailTemplateConfiguration.getDefaultSender())
				.to(user.getEmail())
				.subject("email.send-credentials.subject")
				.template("emails/sendCredentials")
				.templateParam("username", user.getUsername())
				.templateParam("password", password)
				.html(true)
				.build();
		mailService.sendMail(simpleMail);
	}

	public void requestPasswordChange(final String email) throws ServiceException {
		log.debug("requestPasswordChange");

		final Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isPresent()) {
			log.debug("Usuario encontrado -> creacion de token temporal");

			String token = UUID.randomUUID().toString();
			createPasswordResetTokenForUser(optionalUser.get(), passwordManagementService.encode(token));

			final TemplateMail simpleMail = TemplateMail.builder()
					.from(mailTemplateConfiguration.getDefaultSender())
					.to(optionalUser.get().getEmail())
					.subject("email.reset-password.subject")
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

		if (passwordResetToken == null) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception("Error: Token for password not found");
		}

		changeUserPassword(passwordResetToken.getUser(), passwordDTO.getNewPassword());
		return "OK";
	}

	private void changeUserPassword(User user, String password) {
		user.setPassword(passwordManagementService.encode(password));
		userRepository.save(user);
	}

	private Token findPasswordResetToken(String token) {
		final List<Token> listToken = tokenRepository.findByTokenExpirationDateAfter(LocalDateTime.now());
		return listToken.stream()
				.filter(p -> passwordManagementService.matches(token, p.getValue()))
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
			if (checkIfValidOldPassword(user.get(), passwordDTO.getPassword())) {
				changeUserPassword(user.get(), passwordDTO.getNewPassword());
			} else {
				throw ServiceExceptionCatalog.INVALID_LOGIN_EXCEPTION
						.exception("Error: Invalid password");
			}
		} else {
			throw ServiceExceptionCatalog.INVALID_LOGIN_EXCEPTION
					.exception("Error: Username not found while requesting password change");
		}

		return "OK";
	}

	private boolean checkIfValidOldPassword(User user, String password) {
		return passwordManagementService.matches(password, user.getPassword());
	}

	public boolean existUsername(String username){
		return userRepository.existsByUsername(username);
	}

	public boolean existUserEmail(String email){
		return userRepository.existsByEmail(email);
	}

	public Page<UserDTO> findUsersBySearch(String search, Pageable pageable) {
		Page<User> users = userRepository.findUsersBySearch(search, pageable);
		return users.map(userMapper::userToUserDTOConverter);
	}

	public Page<UserDTO> filterUsers(String user, Pageable pageable) {
		UserDTO userDTO = UserMapper.INSTANCE.jsonToUserDTOConverter(user);
		log.debug("Llamando al servicio...");

		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase(true).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		User userEx = userMapper.userDTOToUserConverter(userDTO);

		Example<User> spec = Example.of(userEx, matcher);
		Page<User> users = userRepository.findAll(spec, pageable);

		return users.map(userMapper::userToUserDTOConverter);
	}

	public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {
		UserDTO userDTO = userMapper.userUpdateDTOToUserDTOConverter(userUpdateDTO);
		Optional<User> userOri= userRepository.findById(userDTO.getId());

		User user = userMapper.userDTOToUserConverter(userDTO);

		if (userOri.isPresent()){
			user.setPassword(userOri.get().getPassword());
			user.setRoles(userOri.get().getRoles());
			user.setHospital(userOri.get().getHospital());
			user.setService(userOri.get().getService());
			user.setActive(userOri.get().isActive());

			if (user.getCollegeNumber() == null) {
				user.setCollegeNumber(userOri.get().getCollegeNumber());
			}
			if (user.getDni() == null) {
				user.setDni(userOri.get().getDni());
			}
			if (user.getEmail() == null) {
				user.setEmail(userOri.get().getEmail());
			}
			if (user.getName() == null) {
				user.setUsername(userOri.get().getUsername());
			}
			if (user.getSurname() == null) {
				user.setSurname(userOri.get().getSurname());
			}
			if (user.getUsername() == null) {
				user.setUsername(userOri.get().getUsername());
			}
			if (user.getPhone() == null) {
				user.setPhone(userOri.get().getPhone());
			}
		}

		return userMapper.userToUserDTOConverter(userRepository.save(user));
	}

	public void deleteUser(Long id) {

		log.debug(String.format("Llamando a la BD para borrar el registro de usuarios con id=%d...", id));
		userRepository.deleteById(id);
	}

	private void checkServiceExistence(UserDTO userDTO) {
		if (userDTO.getServiceId() != null) {
			final Optional<es.plexus.hopes.hopesback.repository.model.Service> service = serviceService
					.getOneServiceById(userDTO.getServiceId());

			if (!service.isPresent()) {
				throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
						String.format("Servicio con el id %d no encontrado. El servicio es requerido en usuarios con número de colegiado",
								userDTO.getServiceId()));
			}
		} else if (userDTO.getCollegeNumber() != null) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception("El servicio es requerido en usuarios con número de colegiado");
		}
	}
}
