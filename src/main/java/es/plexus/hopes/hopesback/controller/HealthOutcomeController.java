package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.service.HealthOutcomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "Controlador de Healthoutcome", tags = "health-outcomes")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/health-outcomes")
public class HealthOutcomeController {
	
	static final String FIND_RESULTS_BY_TYPE = "/find-results-by-types";
	static final String GET_DETAIL_RESULTS_BY_TYPE = "/get-detail-results-by-type";
	static final String GET_DETAIL_RESULTS_BY_TYPE_EXPORT = "/get-detail-results-by-type-export";
	private static final String CALLING_SERVICE = "Calling service...";

	private final HealthOutcomeService healthOutcomeService;

	@ApiOperation("Recuperar los resultados por tipo")
	@GetMapping(FIND_RESULTS_BY_TYPE)
	public Map<String, Long> findResultsByTypes(@RequestParam(value = "type", required = true) String type) {
		log.debug(CALLING_SERVICE);
		return healthOutcomeService.findResultsByTypes(type);
	}
	
	@ApiOperation("Detalle de los resultados por tipo")
	@GetMapping(GET_DETAIL_RESULTS_BY_TYPE)
	public Page<GraphPatientDetailDTO> getDetailsResultsByType(@RequestParam(value = "indexType", required = true) String indexType, @RequestParam(value = "result") String result, @PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return healthOutcomeService.getDetailsResultsByType(indexType, result, pageable);
	}
	
	@ApiOperation("Detalle de los resultados por tipo para exportar")
	@GetMapping(GET_DETAIL_RESULTS_BY_TYPE_EXPORT)
	public List<GraphPatientDetailDTO> getDetailsResultsByType(@RequestParam(value = "indexType", required = true) String indexType, @RequestParam(value = "result") String result) {
		log.debug(CALLING_SERVICE);
		return healthOutcomeService.getDetailsResultsByType(indexType, result);
	}
}
