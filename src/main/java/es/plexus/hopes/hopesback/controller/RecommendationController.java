package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.RecommendationDTO;
import es.plexus.hopes.hopesback.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(RecommendationController.MAPPING)
public class RecommendationController {

	private static final String CALLING_SERVICE = "Calling service...";
	static final String MAPPING = "/recommendations";

	private final RecommendationService recommendationService;

	@GetMapping
	public List<RecommendationDTO> getAllServices() {
		log.debug(CALLING_SERVICE);
		return recommendationService.findAllRecommendation();
	}
}
