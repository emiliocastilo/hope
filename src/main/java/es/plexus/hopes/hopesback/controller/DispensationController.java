package es.plexus.hopes.hopesback.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.plexus.hopes.hopesback.controller.model.DispensationDTO;
import es.plexus.hopes.hopesback.controller.model.FormDispensationDTO;
import es.plexus.hopes.hopesback.service.DispensationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Api(value = "Controlador de dispensaciones", tags = "dispensation")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(DispensationController.DISPENSATION_MAPPING)
public class DispensationController {

	static final String DISPENSATION_MAPPING = "/dispensations";
	static final String FIND_MONTHLY_CONSUME = "/findMonthlyConsume";
	static final String FIND_MONTHLY_CONSUME_ACCUMULATED = "/findMonthlyConsumeAccumulated";
	private static final String CALLING_SERVICE = "Calling service...";

	private final DispensationService dispensationService;

	@ApiOperation("Crear una dispensacion")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public DispensationDTO create(@ModelAttribute final FormDispensationDTO dispensation) {
		log.debug(CALLING_SERVICE);
		return dispensationService.save(dispensation);
	}

	@ApiOperation("Buscar una dispensacion por id")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public DispensationDTO findById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		log.debug(CALLING_SERVICE);
		return dispensationService.findById(id);
	}

	@ApiOperation("Borrar una dispensacion por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@ApiParam(value = "identificador", required = true) @PathVariable Long id) {
		log.debug(CALLING_SERVICE);
		dispensationService.deleteById(id);
	}

	@ApiOperation("Recuperar todas las dispensaciones")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<DispensationDTO> findAll(@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return dispensationService.findAll(pageable);
	}
	
	@ApiOperation("Consumo mensual en euros (Biolgicos)")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(FIND_MONTHLY_CONSUME)
	public Map<String, Map<String, String>> findMonthlyConsume(
			@ApiParam(value = "Préfijo ATC")
			@RequestParam(value = "code", required = true, defaultValue = "") final String code,
			@ApiParam(value = "Años atrás")
			@RequestParam(value = "lastYears", required = false, defaultValue = "2") final Integer lastYears) {
		log.debug(CALLING_SERVICE +" "+ FIND_MONTHLY_CONSUME);
		return dispensationService.findMonthlyConsume(code, lastYears);
	}
	
	@ApiOperation("Consumo mensual acumulado en euros (Biolgicos)")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(FIND_MONTHLY_CONSUME_ACCUMULATED)
	public Map<String, Map<String, String>> findMonthlyConsumeAcumulated(
			@ApiParam(value = "Préfijo ATC")
			@RequestParam(value = "code", required = true, defaultValue = "") final String code,
			@ApiParam(value = "Años atrás")
			@RequestParam(value = "lastYears", required = false, defaultValue = "2") final Integer lastYears) {
		log.debug(CALLING_SERVICE +" "+ FIND_MONTHLY_CONSUME);
		return dispensationService.findMonthlyConsumeAcumulated(code, lastYears);
	}

}
