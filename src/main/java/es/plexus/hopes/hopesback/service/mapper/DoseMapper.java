package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.DoseDTO;
import es.plexus.hopes.hopesback.repository.model.Dose;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoseMapper {

    DoseMapper INSTANCE = Mappers.getMapper(DoseMapper.class);

    DoseDTO entityToDto(Dose entity);
}
