package es.plexus.hopes.hopesback.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;

@Mapper
public interface PatientTreatmentMapper {

	static final String ERROR_GET_VALUE_FIELD = "Error getting value field for treatment";
    PatientTreatmentMapper INSTANCE = Mappers.getMapper(PatientTreatmentMapper.class);

    @Named("treatmentListToTreatmentInfoDTOList")
	default List<TreatmentInfoDTO> treatmentListToTreatmentInfoDTOList(List<Object[]> treatmentList) {
    	
    	List<TreatmentInfoDTO> listTreatmentInfoDTO = new ArrayList<>();
    	
		for (Object[] treatment : treatmentList) {
			TreatmentInfoDTO treatmentInfoDTO =  new TreatmentInfoDTO();
			treatmentInfoDTO.setCodeAct((String)treatment[0]);
			treatmentInfoDTO.setDescription((String)treatment[1]);
			treatmentInfoDTO.setCount(((Long)treatment[2]).intValue());
			
			listTreatmentInfoDTO.add(treatmentInfoDTO);
		}
					
		return listTreatmentInfoDTO;
	}
}
