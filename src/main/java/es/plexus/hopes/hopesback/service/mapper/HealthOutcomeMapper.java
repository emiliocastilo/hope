package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.GraphHealthOutcomeDTO;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import org.mapstruct.Mapper;

@Mapper
public interface HealthOutcomeMapper {
    GraphHealthOutcomeDTO entityToGraphHealthOutcomeDto(HealthOutcome entity);
}
