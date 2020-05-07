package es.plexus.hopes.hopesback.service.mapper;

import java.util.Locale;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.repository.model.LocalizedRole;
import es.plexus.hopes.hopesback.repository.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	@Named("roleToRoleDTOConverter")
	default RoleDTO roleToRoleDTOConverter(final Role role) {		
				
		if(role == null) {
			return null;
		}
		
		RoleDTO roleDTO = new RoleDTO();
		
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		roleDTO.setDescription(role.getDescription());
		
		String language = Locale.getDefault().getLanguage();
		
		LocalizedRole localizedRole = role.getLocalizations().stream()
		  .filter(localization -> language.equals(localization.getLocale()))
		  .findAny()
		  .orElse(null);
		
		if(null != localizedRole) {
			roleDTO.setName(localizedRole.getName());
			roleDTO.setDescription(localizedRole.getDescription());
		}
				
		return roleDTO;		
	}

	Role roleDTOToRoleConverter(final RoleDTO roleDTO);

}
