package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.repository.model.FormMongo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InputMapper.class)
public interface FormMapper {

    FormMongo dtoToEntity(FormDTO dto);

    FormDTO entityToDto(FormMongo entity);
    
    List<FormDTO> listEntityToListDto(List<FormMongo> entities);
}
