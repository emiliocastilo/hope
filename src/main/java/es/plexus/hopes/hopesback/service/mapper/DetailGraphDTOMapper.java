package es.plexus.hopes.hopesback.service.mapper;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;

@Mapper(componentModel = "spring")
public interface DetailGraphDTOMapper {

	DetailGraphDTOMapper INSTANCE = Mappers.getMapper(DetailGraphDTOMapper.class);

	@Named("objectToDetailGraphDTOConventer")
	default DetailGraphDTO objectToDetailGraphDTOConventer(Object[] detailGraph) {
		DetailGraphDTO detailGraphDTO = new DetailGraphDTO();

		detailGraphDTO.setNhc((String)detailGraph[0]); //Nhc
		detailGraphDTO.setSip((String)detailGraph[1]); //Dni
		detailGraphDTO.setPatient((String)detailGraph[2] //Name
				+ " " + (String)detailGraph[3] //FirstName
				+ " " + (String)detailGraph[4]);
		detailGraphDTO.setIndication((String)detailGraph[5]); //Indication tratment
		detailGraphDTO.setDiagnostig((String)detailGraph[6]); //Indication diagnostig
		detailGraphDTO.setTreatment((String)detailGraph[7]); //actIngredients medicine
		String type = (String)detailGraph[8];
		if(type.equals("PASI")) {
			detailGraphDTO.setPasi((String)detailGraph[9]);
			detailGraphDTO.setDatePasi((LocalDateTime)detailGraph[10]);
		} else if(type.equals("DLQI")) {
			detailGraphDTO.setDlqi((String)detailGraph[9]);
			detailGraphDTO.setDateDlqi((LocalDateTime)detailGraph[10]);
		}
		
		return detailGraphDTO;
	}

}

