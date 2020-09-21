package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.PatientDiagnosisDTO;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientDiagnosisMapper {

    PatientDiagnosisMapper INSTANCE = Mappers.getMapper(PatientDiagnosisMapper.class);

    PatientDiagnosisDTO entityToDto(PatientDiagnose entity);

    PatientDiagnose dtoToEntity(PatientDiagnosisDTO dto);

}
