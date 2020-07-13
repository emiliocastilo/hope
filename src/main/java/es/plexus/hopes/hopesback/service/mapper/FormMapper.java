package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.repository.model.FormMongo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = InputMapper.class)
public interface FormMapper {

    FormMongo dtoToEntity(FormDTO dto);

    FormDTO entityToDto(FormMongo entity);

    List<FormDTO> listEntityToListDto(List<FormMongo> entities);
}
