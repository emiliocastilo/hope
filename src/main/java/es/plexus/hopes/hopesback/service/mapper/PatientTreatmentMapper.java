package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.GroupingFieldTreatmentInfoDTO;
import es.plexus.hopes.hopesback.controller.model.PatientTreatmentDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PatientTreatmentMapper {

    PatientTreatmentMapper INSTANCE = Mappers.getMapper(PatientTreatmentMapper.class);

    @Named("treatmentListToTreatmentInfoDTOList")
	default List<TreatmentInfoDTO> treatmentListToTreatmentInfoDTOList(List<Object[]> treatmentList) {
    	
    	List<TreatmentInfoDTO> listTreatmentInfoDTO = new ArrayList<>();
    	
		for (Object[] treatment : treatmentList) {
			TreatmentInfoDTO treatmentInfoDTO =  new TreatmentInfoDTO();
			treatmentInfoDTO.setCodeAct((String)treatment[0]);
			treatmentInfoDTO.setActIngredients((String)treatment[1]);
			treatmentInfoDTO.setCount(((Long)treatment[2]).intValue());
			
			listTreatmentInfoDTO.add(treatmentInfoDTO);
		}
					
		return listTreatmentInfoDTO;
	}
	@Mappings({
			@Mapping(source = "type", target = "groupingField")
	})
	GroupingFieldTreatmentInfoDTO entityToTypeTreatmentInfoDto(PatientTreatment entity);

	@Mappings({
			@Mapping(source = "reason", target = "groupingField")
	})
	GroupingFieldTreatmentInfoDTO entityToReasonTreatmentInfoDto(PatientTreatment entity);

	PatientTreatmentDTO entityToPatientTreamentDto(PatientTreatment entity);
}
