package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ServiceMapper.class)
public interface HospitalMapper {

	HospitalDTO hospitalToHospitalDTOConverter(final Hospital hospital);

	Hospital hospitalDTOToHospitalConverter(final HospitalDTO hospitalDTO);
}
