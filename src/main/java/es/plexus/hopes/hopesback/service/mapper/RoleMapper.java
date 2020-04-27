package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.repository.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleDTO roleToRoleDTOConverter(final Role role);

	Role roleDTOToRoleConverter(final RoleDTO roleDTO);

}
