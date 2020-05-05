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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.repository.RoleRepository;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.service.mapper.RoleMapper;

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

	public List<RoleDTO> getAllRoles() {
		log.debug("Calling BD. Give all roles...");
		return roleRepository.findAll().stream().map(roleMapper::roleToRoleDTOConverter).collect(Collectors.toList());
	}

	public RoleDTO getOneRoleById(final Long id) {
		final Optional<Role> role = getOneRoleByIdCommon(id);

		RoleDTO roleDTO = null;

		if (role.isPresent()) {
			roleDTO = roleMapper.roleToRoleDTOConverter(role.get());
		} else {
			log.error(String.format("RoleDto with id = %s not found ...", id));
		}

		return roleDTO;
	}

	public RoleDTO addRole(final RoleDTO roleDTO) {
		Role role = addRoleCommon(roleDTO);

		log.debug("Calling DB. Save record.");
		role = roleRepository.save(role);

		return roleMapper.roleToRoleDTOConverter(role);
	}

	public RoleDTO updateRole(final RoleDTO roleDTO) throws ServiceException {
		checkRoleExistence(roleDTO.getId());

		Role role = addRoleCommon(roleDTO);
		log.debug("Calling DB. Update record with id=" + roleDTO.getId());
		role = roleRepository.save(role);

		return roleMapper.roleToRoleDTOConverter(role);
	}

	public void deleteRole(final Long id) throws ServiceException {
		checkRoleExistence(id);

		log.debug("Calling DB. Delete record with id=" + id);
		roleRepository.deleteById(id);
	}

	public Page<RoleDTO> findRolesBySearch(final String search, final Pageable pageable) {
		log.debug("Calling DB...");
		Page<Role> rolePage = roleRepository.findByNameContainingIgnoreCase(search, pageable);

		return rolePage.map(roleMapper::roleToRoleDTOConverter);
	}

	public Set<Role> getAllRolesByIdSet(final Set<Long> roleIdSet) {
		final Set<Role> roleSet = new HashSet<>();
		roleIdSet.forEach(aLong -> getOneRoleById(aLong).ifPresent(roleSet::add));
		return roleSet;
	}

	private Optional<Role> getOneRoleById(Long aLong) {
		return roleRepository.findById(aLong);
	}

	public Optional<RoleDTO> getRoleByName(String name) {
		Role role = roleRepository.findByName(name).orElse(null);
		return Optional.of(roleMapper.roleToRoleDTOConverter(role));
	}
}
