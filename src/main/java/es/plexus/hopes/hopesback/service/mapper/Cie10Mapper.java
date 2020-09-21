package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.CieDTO;
import es.plexus.hopes.hopesback.repository.model.Cie10;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Cie10Mapper {

    Cie10Mapper INSTANCE = Mappers.getMapper(Cie10Mapper.class);

    CieDTO entityToDto(Cie10 entity);

    Cie10 dtoToEntity(CieDTO dto);

}
