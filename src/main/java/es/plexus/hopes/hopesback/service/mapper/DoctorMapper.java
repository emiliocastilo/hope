package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.Service;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@DecoratedWith(DoctorEntitiesMappingResolver.class)
public interface DoctorMapper {

	@Mappings({
			@Mapping(source = "service", target = "serviceId", qualifiedByName = "serviceToLongIdConverter"),
			@Mapping(source = "user.username", target = "username"),
			@Mapping(source = "user.password", target = "password"),
			@Mapping(source = "user.email", target = "email"),
			@Mapping(source = "user.hospital", target = "hospitalId", qualifiedByName = "userToLongIdConverter"),
			@Mapping(source = "user.roles", target = "roleList", qualifiedByName = "rolesToLongIdListConverter")
	})
	DoctorDTO doctorToDoctorDTOConverter(final Doctor doctor);

	Doctor doctorDTOToDoctorConverter(final DoctorDTO doctorDTO);

	RoleDTO roleDTOToRoleConverter(final Role user);

	Role roleToRoleDTOConverter(final RoleDTO userDTO);

	@Named("serviceToLongIdConverter")
	default Long serviceToLongIdConverter(Service service) {
		return service.getId();
	}

	@Named("userToLongIdConverter")
	default Long userToLongIdConverter(Hospital hospital) {
		return hospital.getId();
	}

	@Named("rolesToLongIdListConverter")
	default List<Long> rolesToLongIdListConverter(Set<Role> roles) {
		return roles.stream().map(Role::getId).collect(Collectors.toList());
	}
}
