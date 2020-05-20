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

		detailGraphDTO.setNhc(patientTreatment.getPatient().getNhc()); 
		detailGraphDTO.setSip(patientTreatment.getPatient().getHealthCard()); 
		detailGraphDTO.setPatient(patientTreatment.getPatient().getName() 
				+ " " + patientTreatment.getPatient().getFirstSurname() 
				+ " " + patientTreatment.getPatient().getLastSurname());
		detailGraphDTO.setIndication(patientTreatment.getIndication()); 
		detailGraphDTO.setDiagnostig(patientTreatment.getPatientDiagnose().getIndication()); 
		detailGraphDTO.setTreatment(patientTreatment.getMedicine().getActIngredients()); 
		
		return detailGraphDTO;
	}


}

