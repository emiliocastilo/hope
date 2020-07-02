package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.CieDTO;
import es.plexus.hopes.hopesback.repository.model.Cie9;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Cie9Mapper {

    Cie9Mapper INSTANCE = Mappers.getMapper(Cie9Mapper.class);

    CieDTO entityToDto(Cie9 entity);

    Cie9 dtoToEntity(CieDTO dto);

}
