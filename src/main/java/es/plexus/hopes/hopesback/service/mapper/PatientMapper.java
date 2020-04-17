package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDTO entityToDto(Patient entity);

    Patient dtoToEntity(PatientDTO dto);

    List<PatientDTO> entityListToDtoList (List<Patient> entityList);
    List<Patient> dtoListToEntityList(List<PatientDTO> dtoList);

}
