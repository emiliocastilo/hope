package es.plexus.hopes.hopesback.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;

@Mapper(componentModel = "spring")
public interface DetailGraphDTOMapper {

	DetailGraphDTOMapper INSTANCE = Mappers.getMapper(DetailGraphDTOMapper.class);

	@Named("patientTreatmentToDetailGraphDTOConventer")
	default DetailGraphDTO patientTreatmentToDetailGraphDTOConventer(PatientTreatment patientTreatment) {
		DetailGraphDTO detailGraphDTO = new DetailGraphDTO();

		detailGraphDTO.setNhc(patientTreatment.getPatient().getNhc()); //Nhc
		detailGraphDTO.setSip(patientTreatment.getPatient().getHealthCard()); //HealthCard
		detailGraphDTO.setPatient(patientTreatment.getPatient().getName() //Name
				+ " " + patientTreatment.getPatient().getFirstSurname() //FirstName
				+ " " + patientTreatment.getPatient().getLastSurname());
		detailGraphDTO.setIndication(patientTreatment.getIndication()); //Indication tratment
		detailGraphDTO.setDiagnostig(patientTreatment.getPatientDiagnose().getIndication().getDescripcion()); //Indication diagnostig
		detailGraphDTO.setTreatment(patientTreatment.getMedicine().getActIngredients()); //actIngredients medicine
		/*String type = (String)detailGraph[8];
		if(type.equals("PASI")) {
			detailGraphDTO.setPasi((String)detailGraph[9]);
			detailGraphDTO.setDatePasi((LocalDateTime)detailGraph[10]);
		} else if(type.equals("DLQI")) {
			detailGraphDTO.setDlqi((String)detailGraph[9]);
			detailGraphDTO.setDateDlqi((LocalDateTime)detailGraph[10]);
		}*/
		
		return detailGraphDTO;
	}

}

