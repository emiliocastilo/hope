package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.TemplateDTO;
import es.plexus.hopes.hopesback.repository.model.TemplateMongo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    TemplateDTO entityToDto(TemplateMongo entity);

    TemplateMongo dtoToEntity(TemplateDTO dto);
}
