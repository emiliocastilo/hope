package es.plexus.hopes.hopesback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.repository.HealthOutcomeRepository;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice.Return;

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

	public List<PatientDosesInfoDTO> infoPatientsDoses() {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.infoPatientsDoses();
	}

	//public Page<DetailGraphDTO> detailsDrapths(final Pageable pageable) {
	public Page<String> detailsDrapths(final String type, final String  indication, final Pageable pageable) {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.detailsDrapths(type, indication, pageable);
	}
}
