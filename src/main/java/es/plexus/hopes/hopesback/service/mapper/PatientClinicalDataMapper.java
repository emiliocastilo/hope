package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.PatientClinicalDataDTO;
import es.plexus.hopes.hopesback.repository.model.PatientClinicalData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientClinicalDataMapper {
    PatientClinicalDataMapper INSTANCE = Mappers.getMapper(PatientClinicalDataMapper.class);

    PatientClinicalDataDTO entityToDto(PatientClinicalData entity);

    PatientClinicalData patientClinicalDataDtoToEntity(PatientClinicalDataDTO dto);
}
