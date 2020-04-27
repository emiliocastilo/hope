package es.plexus.hopes.hopesback.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import org.hibernate.service.spi.ServiceException;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DoctorDTOMapper {

    DoctorDTOMapper INSTANCE = Mappers.getMapper(DoctorDTOMapper.class);

    @Named("jsonToDoctorDTOConventer")
    default DoctorDTO jsonToDoctorDTOConventer(String doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        try {
        	doctorDTO = new ObjectMapper().readValue(doctor, DoctorDTO.class);
        } catch (JsonMappingException e) {
			throw new ServiceException("Fields not belonging to the object are being sent " + e.getMessage());
		} catch (JsonProcessingException e) {
			throw new ServiceException("Filter processing error occurred " + e.getMessage());
		}

        return doctorDTO;
    }
 
}
