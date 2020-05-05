package es.plexus.hopes.hopesback.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mappings({
			@Mapping(source = "hospital", target = "hospitalId", qualifiedByName = "userToHospitalIdConverter"),
			@Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRoleIdListConverter")
	})
	UserDTO userToUserDTOConverter(final User user);

	@Mappings({
		@Mapping(source = "hospital", target = "hospitalId", qualifiedByName = "userToHospitalIdConverter"),
		@Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRoleIdListConverter")
	})
	@Named("userToUserSimpleDTOConverter")
	UserSimpleDTO userToUserSimpleDTOConverter(final User user);
	
	@Mappings({
			@Mapping(target = "roles", ignore = true),
			@Mapping(target = "hospital", ignore = true)
	})
	User userDTOToUserConverter(final UserDTO userDTO);

	@Named("userToHospitalIdConverter")
	default Long userToHospitalIdConverter(Hospital hospital) {
		return hospital.getId();
	}

	@Named("rolesToRoleIdListConverter")
	default Set<Long> rolesToRoleIdListConverter(Set<Role> roles) {
		return roles.stream().map(Role::getId).collect(Collectors.toSet());
	}
}
