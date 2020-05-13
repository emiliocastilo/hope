package es.plexus.hopes.hopesback.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import org.hibernate.service.spi.ServiceException;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InputMapper {

    default String dtosToJSON(List<InputDTO> dtos) {

        String data = "";

        if (dtos != null && !dtos.isEmpty()) {
            try {
                data = new ObjectMapper().writeValueAsString(dtos);
            } catch (JsonProcessingException e) {
                throw new ServiceException("Filter processing error occurred " + e.getMessage());
            }
        }

        return data;
    }

    default List<InputDTO> jsonToDtos(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, InputDTO.class));
        } catch (JsonProcessingException e) {
            throw new ServiceException("Filter processing error occurred " + e.getMessage());
        }
    }
}
