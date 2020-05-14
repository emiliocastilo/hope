package es.plexus.hopes.hopesback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientTreatmentService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final PatientTreatmentRepository patientTreatmentRepository;

	public List<TreatmentInfoDTO> patientsUnderChemicalTreatment(String type, String indication) {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.patientsUnderTreatment(type, indication);
	}

}
