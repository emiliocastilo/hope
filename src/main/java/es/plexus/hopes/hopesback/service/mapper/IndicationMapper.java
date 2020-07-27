package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.IndicationDTO;
import es.plexus.hopes.hopesback.repository.model.Indication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IndicationMapper {

    IndicationMapper INSTANCE = Mappers.getMapper(IndicationMapper.class);

    IndicationDTO entityToDto(Indication entity);

    Indication dtoToEntity(IndicationDTO dto);

}
