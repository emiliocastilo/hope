package es.plexus.hopes.hopesback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.service.GraphsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/graphs")
public class GraphsController {
	private static final String CALLING_SERVICE = "Calling service...";

	private final GraphsService graphsService;

	@GetMapping("/patients-under-treatment")
	public List<TreatmentInfoDTO> patientsUnderChemicalTreatment(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "indication", required = false) String indication) {
		log.debug(CALLING_SERVICE);
		return graphsService.patientsUnderChemicalTreatment(type, indication);
	}

	@GetMapping("/health-outcomes-by-types")
	public List<HealthOutcomeDTO> healthOutcomesByType(@RequestParam(value = "type", required = true) String type) {
		log.debug(CALLING_SERVICE);
		return graphsService.healthOutcomesByType(type);
	}
	
	@GetMapping("/info-patients-doses")
	public List<PatientDosesInfoDTO> infoPatientsDoses() {
		log.debug(CALLING_SERVICE);
		return graphsService.infoPatientsDoses();
	}
	
}
