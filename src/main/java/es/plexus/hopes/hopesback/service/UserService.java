package es.plexus.hopes.hopesback.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.User;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.UserMapper;

@Service
public class UserService {

	private final UserMapper userMapper;
	private final RoleService roleService;
	private final UserRepository userRepository;
	private final HospitalService hospitalService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(final UserRepository userRepository, final UserMapper userMapper,
					   final RoleService roleService, final HospitalService hospitalService,
					   final BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.roleService = roleService;
		this.hospitalService = hospitalService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

	public UserSimpleDTO getOneSimpleUserByName(final String name, String roleName) {
		UserSimpleDTO userSimpleDTO = null;

		final Optional<User> user = userRepository.findByUsername(name);

		if (user.isPresent()) {
			userSimpleDTO = userMapper.userToUserSimpleDTOConverter(user.get());
			Optional<RoleDTO> role = roleService.getRoleByName(roleName);
			if(role.isPresent()) {
				userSimpleDTO.setRolSelected(role.get());
			}
		}

		return userSimpleDTO;
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
}
