package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.PatientClinicalDataDTO;
import es.plexus.hopes.hopesback.repository.model.PatientClinicalData;
import org.mapstruct.factory.Mappers;

public interface PatientClinicalDataMapper {
    PatientClinicalDataMapper INSTANCE = Mappers.getMapper(PatientClinicalDataMapper.class);

    PatientClinicalDataDTO entityToDto(PatientClinicalData entity);
}
