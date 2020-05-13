package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.controller.model.DoctorUpdateDTO;
import es.plexus.hopes.hopesback.controller.model.DoctorViewDTO;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ServiceMapper.class})
public interface DoctorMapper {

	@Mappings({
			@Mapping(source = "user", target = "userDTO"),
			@Mapping(source = "service", target = "serviceDTO")
	})
	DoctorViewDTO doctorToDoctorDTOConverter(final Doctor doctor);

	@Mappings({
			@Mapping(target = "user", ignore = true),
			@Mapping(source = "serviceDTO", target = "service")
	})
	Doctor doctorDTOToDoctorConverter(final DoctorDTO doctorDTO);

	@Mappings({
			@Mapping(target = "user", ignore = true),
			@Mapping(source = "serviceDTO", target = "service")
	})
	Doctor doctorUpdateDTOToDoctorConverter(final DoctorUpdateDTO doctorDTO);

}
