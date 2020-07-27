package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.PatientTreatmentDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatientTreatmentMapper {

    PatientTreatmentMapper INSTANCE = Mappers.getMapper(PatientTreatmentMapper.class);

    TreatmentDTO entityToTreatmentDTO(PatientTreatment entity);

    PatientTreatmentDTO entityToDto(PatientTreatment entity);

    PatientTreatment dtoToEntity(PatientTreatmentDTO dto);
}

