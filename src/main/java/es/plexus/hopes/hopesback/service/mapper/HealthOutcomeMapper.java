package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.GraphHealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HealthOutcomeMapper {
	
	HealthOutcomeMapper INSTANCE = Mappers.getMapper(HealthOutcomeMapper.class);
	
    GraphHealthOutcomeDTO entityToGraphHealthOutcomeDto(HealthOutcome entity);
    
    @Mapping(target = "patientId", source = "patient.id")
    HealthOutcomeDTO entityToHealthOutcomeDto(HealthOutcome entity);
    
    List<HealthOutcome> dtoToEntities(List<HealthOutcomeDTO> dto);
    
    HealthOutcome dtoToEntity(HealthOutcomeDTO dto);
    
    List<HealthOutcomeDTO> entityToHealthOutcomeDtos(List<HealthOutcome> entity);
}
