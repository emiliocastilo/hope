package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.RecommendationDTO;
import es.plexus.hopes.hopesback.service.RecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Controlador de la recomendaciones", tags = "recommentaion")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(RecommendationController.RECOMMENDATION_MAPPING)
public class RecommendationController {

	static final String RECOMMENDATION_MAPPING = "/recommendations";
	private static final String CALLING_SERVICE = "Calling service...";

	private final RecommendationService recommendationService;

	@ApiOperation("Recuperar todas las recomendaciones")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<RecommendationDTO> getAllServices() {
		log.debug(CALLING_SERVICE);
		return recommendationService.findAllRecommendation();
	}
}
