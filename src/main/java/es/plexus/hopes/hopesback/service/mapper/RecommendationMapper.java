package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.RecommendationDTO;
import es.plexus.hopes.hopesback.repository.model.Recommendation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RecommendationMapper {

	RecommendationMapper INSTANCE = Mappers.getMapper(RecommendationMapper.class);

	RecommendationDTO entityToDto(final Recommendation dto);

	Recommendation dtoToEntity(final RecommendationDTO entity);
}
