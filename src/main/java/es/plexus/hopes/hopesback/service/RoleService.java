package es.plexus.hopes.hopesback.service;

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

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;

	@Autowired
	public RoleService(final RoleRepository roleRepository, final RoleMapper roleMapper) {
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
	}

	public List<RoleDTO> getAllRoles() {
		return roleRepository.findAll().stream().map(roleMapper::roleToRoleDTOConverter).collect(Collectors.toList());
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
