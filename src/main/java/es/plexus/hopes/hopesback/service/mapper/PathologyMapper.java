package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.PathologyDTO;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PathologyMapper {

    PathologyMapper INSTANCE = Mappers.getMapper(PathologyMapper.class);

    PathologyDTO entityToDto(Pathology entity);

    Pathology dtoToEntity(PathologyDTO dto);

}
