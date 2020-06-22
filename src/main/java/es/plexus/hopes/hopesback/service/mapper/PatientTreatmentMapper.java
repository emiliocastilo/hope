package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.TreatmentDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientTreatmentMapper {

	TreatmentDTO entityToTreatmentDTO(PatientTreatment entity);
}

