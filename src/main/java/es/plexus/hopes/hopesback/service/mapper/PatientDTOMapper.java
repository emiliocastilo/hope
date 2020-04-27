package es.plexus.hopes.hopesback.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import org.hibernate.service.spi.ServiceException;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatientDTOMapper {

	PatientDTOMapper INSTANCE = Mappers.getMapper(PatientDTOMapper.class);

	@Named("jsonToPatientDTOConventer")
	default PatientDTO jsonToPatientDTOConventer(String patient) {
		PatientDTO patientDTO;
		try {
			patientDTO = new ObjectMapper().readValue(patient, PatientDTO.class);
		} catch (JsonMappingException e) {
			throw new ServiceException("Fields not belonging to the object are being sent " + e.getMessage());
		} catch (JsonProcessingException e) {
			throw new ServiceException("Filter processing error occurred " + e.getMessage());
		}

		return patientDTO;
	}

}
