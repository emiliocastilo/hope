package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ServiceMapper.class})
public interface DoctorMapper {

	DoctorDTO doctorToDoctorDTOConverter(final Doctor doctor);

	@Mapping(target = "service", ignore = true)
	@Mapping(target = "user", ignore = true)
	Doctor doctorDTOToDoctorConverter(final DoctorDTO doctorDTO);

	Doctor doctorDTOToDoctorConverterSearch(DoctorDTO doctorDTO);
}
