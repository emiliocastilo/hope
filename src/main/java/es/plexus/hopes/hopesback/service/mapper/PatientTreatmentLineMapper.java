package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.PatientTreatmentDTO;
import es.plexus.hopes.hopesback.controller.model.PatientTreatmentLineDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.repository.model.PatientTreatmentLine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatientTreatmentLineMapper {

    PatientTreatmentLineMapper INSTANCE = Mappers.getMapper(PatientTreatmentLineMapper.class);

    PatientTreatmentLineDTO entityToDto(PatientTreatmentLine entity);

    PatientTreatmentLine dtoToEntity(PatientTreatmentLineDTO dto);
}

