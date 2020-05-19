package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.service.PatientDiagnosisService;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(PatientDiagnosisController.PATIENT_DIAGNOSE_MAPPING)
public class PatientDiagnosisController {
	static final String PATIENT_DIAGNOSE_MAPPING = "/patients-diagnoses";
	static final String PATIENT_DIAGNOSE_INDICATIONS = "/indications";
	static final String PATIENT_DIAGNOSE_CI9 = "/cie9";
	static final String PATIENT_DIAGNOSE_CI10 = "/cie10";
	static final String PATIENT_DIAGNOSE_TREATMENT = "/treatments";
	static final String PATIENT_DIAGNOSE_COMBINED_TREATMENT = "/combined-treatments";
	static final String PATIENT_DIAGNOSE_END_CAUSE = "/end-causes";
	static final String PATIENT_DIAGNOSE_END_CAUSE_LAST_YEARS = "/end-causes-last-years";
	static final String PATIENT_DIAGNOSE_NUMBER_CHANGES = "/number-changes";
	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientDiagnosisService patientDiagnosisService;
	private final PatientTreatmentService patientTreatmentService;

	@GetMapping(PATIENT_DIAGNOSE_INDICATIONS)
	public Map<String, Long> findPatientsDiagnosesByIndications() {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findPatientsByIndication();
	}

	@GetMapping(PATIENT_DIAGNOSE_CI9)
	public Map<String, Long> findPatientsDiagnosesByCie9(){
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findPatientsByCie9();
	}

	@GetMapping(PATIENT_DIAGNOSE_CI10)
	public Map<String, Long> findPatientsDiagnosesByCie10(){
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findPatientsByCie10();
	}

	@GetMapping(PATIENT_DIAGNOSE_TREATMENT)
	public Map<String, Long> findPatientTreatmentByTreatment() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByTreatment();
	}

	@GetMapping(PATIENT_DIAGNOSE_COMBINED_TREATMENT)
	public Map<String, Long> findPatientTreatmentByCombinedTreatment() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByCombinedTreatment();
	}

	@GetMapping(PATIENT_DIAGNOSE_END_CAUSE)
	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatment(@RequestParam(value = "endCause") String endCause) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatment(endCause);
	}

	@GetMapping(PATIENT_DIAGNOSE_END_CAUSE_LAST_YEARS)
	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(@RequestParam(value = "endCause") String endCause) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(endCause);
	}

	@GetMapping(PATIENT_DIAGNOSE_NUMBER_CHANGES)
	public Map<Long, Integer> findPatientTreatmentByNumberChangesOfBiologicTreatment() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment();
	}
}
