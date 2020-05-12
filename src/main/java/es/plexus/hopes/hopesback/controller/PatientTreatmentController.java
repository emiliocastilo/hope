package es.plexus.hopes.hopesback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/patient_treatments")
public class PatientTreatmentController {
	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientTreatmentService patientTreatmentService;

	@GetMapping("/patients_under_treatment")
	public List<TreatmentInfoDTO> patientsUnderChemicalTreatment(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "indication", required = false) String indication) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.patientsUnderChemicalTreatment(type, indication);

	}

}
