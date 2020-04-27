package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ServiceMapper.class)
public interface HospitalMapper {

	@Mapping(source = "services", target = "serviceDTO")
	HospitalDTO hospitalToHospitalDTOConverter(final Hospital hospital);

	@Mapping(source = "serviceDTO", target = "services")
	Hospital hospitalDTOToHospitalConverter(final HospitalDTO hospitalDTO);
}
