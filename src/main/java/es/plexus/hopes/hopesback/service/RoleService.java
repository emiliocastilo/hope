package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.repository.RoleRepository;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.RoleMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.ROLE_NAME_VIOLATION_CONSTRAINT_EXCEPTION;

@Log4j2
@Service
public class RoleService {

	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;
	private final MenuService menuService;

	@Autowired
	public RoleService(final RoleRepository roleRepository, final RoleMapper roleMapper,
					   final MenuService menuService) {
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
		this.menuService = menuService;
	}

	public Page<RoleDTO> getAllRoles(final Pageable pageable) {
		log.debug("Calling BD. Obtener todos los roles...");
		Page<Role> roles = roleRepository.findAll(pageable);

		return roles.map(roleMapper::roleToRoleDTOConverter);
	}

	public List<RoleDTO> getAllRoles() {
		log.debug("Calling BD. Obtener todos los roles...");
		List<Role> roles = roleRepository.findAll();

		return roles.stream().map(roleMapper::roleToRoleDTOConverter).collect(Collectors.toList());
	}

	public RoleDTO getOneRoleById(final Long id) {
		final Optional<Role> role = getOneRoleByIdCommon(id);

		RoleDTO roleDTO = null;

		if (role.isPresent()) {
			roleDTO = roleMapper.roleToRoleDTOConverter(role.get());
		} else {
			log.error(String.format("RoleDto con id = %s no encontrado ...", id));
		}

		return roleDTO;
	}

	public RoleDTO addRole(final RoleDTO roleDTO) {
		Role role = addRoleCommon(roleDTO);

		log.debug("Llamando DB. Guardando record.");
		existRole(roleDTO);
		role = roleRepository.save(role);

		return roleMapper.roleToRoleDTOConverter(role);
	}

	private void existRole(RoleDTO roleDTO) {

		Role role = null;

		if(null!=roleDTO.getId()){
			Optional<Role> optionalRole = roleRepository.findById(roleDTO.getId());
			role = optionalRole.orElseGet(null);
		}

		boolean isExistName = roleRepository.existsByName(roleDTO.getName());
		if(isExistName && (role == null || !roleDTO.getName().equals(role.getName()))){
			throw ServiceExceptionCatalog.ROLE_NAME_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(ROLE_NAME_VIOLATION_CONSTRAINT_EXCEPTION);
		}

	}

	public RoleDTO updateRole(final RoleDTO roleDTO) throws ServiceException {
		checkRoleExistence(roleDTO.getId());

		Role role = addRoleCommon(roleDTO);
		log.debug("Llamando DB. Registro actualizado con id=" + roleDTO.getId());
		existRole(roleDTO);
		role = roleRepository.save(role);

		return roleMapper.roleToRoleDTOConverter(role);
	}

	public void deleteRole(final Long id) throws ServiceException {
		checkRoleExistence(id);

		log.debug("Llamando a la DB. Registro eliminado con id=" + id);
		roleRepository.deleteById(id);
	}

	public Page<RoleDTO> findRolesBySearch(final String search, final Pageable pageable) {
		log.debug("Llamando a la DB...");
		Page<Role> rolePage = roleRepository.findByNameContainingIgnoreCase(search, pageable);

		return rolePage.map(roleMapper::roleToRoleDTOConverter);
	}

	public Set<Role> getAllRolesByIdSet(final Set<Long> roleIdSet) {
		final Set<Role> roleSet = new HashSet<>();
		roleIdSet.forEach(aLong -> getOneRoleByIdCommon(aLong).ifPresent(roleSet::add));
		return roleSet;
	}

	public Optional<RoleDTO> getRoleByName(String name) {
		Role role = roleRepository.findByName(name).orElse(null);
		return Optional.of(roleMapper.roleToRoleDTOConverter(role));
	}

	public MenuDTO getMenuByRole(final Long id) throws ServiceException {
		Optional<Role> role = getOneRoleByIdCommon(id);

		if (!role.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception(String.format("RoleDto with id = %s not found ...", id));
		}

		MenuDTO menuDTO = menuService.findMenuByRole(Collections.singletonList(role.get().getName()));

		if (menuDTO.getId() == null) {
			log.debug("Arbol no encontrado para rol " + role.get().getName());
			return null;
		}

		return menuDTO;
	}

	private Optional<Role> getOneRoleByIdCommon(Long id) {
		log.debug(String.format("Llamando a la DB. Buscando rol por id = %d", id));
		return roleRepository.findById(id);
	}

	private Role addRoleCommon(RoleDTO roleDTO) {
		return roleMapper.roleDTOToRoleConverter(roleDTO);
	}

	private void checkRoleExistence(Long id) throws ServiceException {
		final Optional<Role> storedRole = getOneRoleByIdCommon(id);
		log.debug("Comprobando si record existe.");
		if (!storedRole.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception(String.format("RoleDto con id = %s no encontrado ...", id));
		}
	}
}
