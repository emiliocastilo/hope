package es.plexus.hopes.hopesback.service.mapper;

import org.mapstruct.Mapper;

import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.repository.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	
	RoleDTO roleToRoleDTOConverter(final Role role);

	Role roleDTOToRoleConverter(final RoleDTO roleDTO);

}
