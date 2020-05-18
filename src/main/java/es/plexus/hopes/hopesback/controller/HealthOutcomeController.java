package es.plexus.hopes.hopesback.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.service.HealthOutcomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/health-outcomes")
public class HealthOutcomeController {
	private static final String CALLING_SERVICE = "Calling service...";

	private final HealthOutcomeService healthOutcomeService;

	@GetMapping("/find-results-by-types")
	public List<HealthOutcomeDTO> findResultsByTypes(@RequestParam(value = "type", required = true) String type) {
		log.debug(CALLING_SERVICE);
		return healthOutcomeService.findResultsByTypes(type);
	}
	
	@GetMapping("/details-results")
	public Page<DetailGraphDTO> detailsResults(@RequestParam(value = "type", required = true) String type, @PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return healthOutcomeService.detailsResults(type, pageable);
	}
}
