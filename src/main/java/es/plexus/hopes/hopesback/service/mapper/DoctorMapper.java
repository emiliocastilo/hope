package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.Service;
import es.plexus.hopes.hopesback.repository.model.User;
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
			@Mapping(source = "service", target = "serviceId", qualifiedByName = "serviceToLongIdConverter")
	})
	DoctorDTO doctorToDoctorDTOConverter(final Doctor doctor);

	List<DoctorDTO> doctorListToDoctorDTOListConverter(final List<Doctor> doctorList);

	Doctor doctorDTOToDoctorConverter(final DoctorDTO doctorDTO);

	@Mappings({
			@Mapping(source = "hospital", target = "hospitalId", qualifiedByName = "userToLongIdConverter"),
			@Mapping(source = "roles", target = "roleList", qualifiedByName = "rolesToLongIdListConverter")
	})
	UserDTO userDTOToUserConverter(final User user);

	User userToUserDTOConverter(final UserDTO userDTO);

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
