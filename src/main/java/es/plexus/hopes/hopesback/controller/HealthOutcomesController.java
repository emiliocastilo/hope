package es.plexus.hopes.hopesback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.service.HealthOutcomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/health_outcomes")
public class HealthOutcomesController {
	private static final String CALLING_SERVICE = "Calling service...";

	private final HealthOutcomeService healthOutcomeService;

	@GetMapping("/get_health_outcomes_by_types")
	public List<HealthOutcomeDTO> getHealthOutcomesByType(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "indication", required = false) String indication) {
		log.debug(CALLING_SERVICE);
		return healthOutcomeService.getHealthOutcomesByType(type);

	}

}
