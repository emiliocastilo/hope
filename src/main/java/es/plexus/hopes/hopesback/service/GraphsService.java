package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.repository.HealthOutcomeRepository;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class GraphsService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final PatientTreatmentRepository patientTreatmentRepository;
	private final HealthOutcomeRepository healthOutcomeRepository;

	public List<TreatmentInfoDTO> patientsUnderChemicalTreatment(String type, String indication) {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.patientsUnderTreatment(type, indication);
	}

	public List<HealthOutcomeDTO> healthOutcomesByType(String type) {
		
		log.debug(CALLING_DB);
		return healthOutcomeRepository.healthOutcomesByType(type);
	}
}