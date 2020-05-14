package es.plexus.hopes.hopesback.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;

@Mapper
public interface HealthOutcomeMapper {

    HealthOutcomeMapper INSTANCE = Mappers.getMapper(HealthOutcomeMapper.class);

    @Named("treatmentListToHealhtOutcomeDTOList")
	default List<HealthOutcomeDTO> treatmentListToHealhtOutcomeDTOList(List<Object[]> treatmentList) {
    	
    	List<HealthOutcomeDTO> listHealhtOutcomeDTO = new ArrayList<>();
    	
		for (Object[] treatment : treatmentList) {
			HealthOutcomeDTO healhtOutcomeDTO =  new HealthOutcomeDTO();
			healhtOutcomeDTO.setResult((String)treatment[0]);
			healhtOutcomeDTO.setCount(((Long)treatment[1]));
			
			listHealhtOutcomeDTO.add(healhtOutcomeDTO);
		}
					
		return listHealhtOutcomeDTO;
	}
}
