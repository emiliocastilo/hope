package es.plexus.hopes.hopesback.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.SectionDTO;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.model.Section;
import org.hibernate.service.spi.ServiceException;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SectionMapper {

	SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

	SectionDTO entityToDto(Section entity);

	Section dtoToEntity(SectionDTO dto);

	default Medicine jsonToEntity(String json) {
		Medicine medicine;

		try {
			medicine = new ObjectMapper().readValue(json, Medicine.class);
		} catch (JsonMappingException e) {
			throw new ServiceException("Fields not belonging to the object are being sent " + e.getMessage());
		} catch (JsonProcessingException e) {
			throw new ServiceException("Filter processing error occurred " + e.getMessage());
		}
		return medicine;
	}
}
