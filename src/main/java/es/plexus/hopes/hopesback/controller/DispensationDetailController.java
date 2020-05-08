package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.service.DispensationDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Controlador para recuperar los detalles de las dispensaciones", tags = "dispensation-detail")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(DispensationDetailController.DISPENSATION_DETAILS_MAPPING)
public class DispensationDetailController {

	static final String DISPENSATION_DETAILS_MAPPING = "/dispensation-details";
	private static final String CALLING_SERVICE = "Llamando al servicio...";

	private final DispensationDetailService dispensationDetailService;

	@ApiOperation("Crear un nuevo detalle para una dispensacion")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public DispensationDetailDTO create(@RequestBody final DispensationDetailDTO dispensation) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.save(dispensation);
	}

	@ApiOperation("Buscar un detalle de la dispensación por id")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public DispensationDetailDTO findById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.findById(id);
	}

	@ApiOperation("Actualizar un detalle de una dispensacion")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public DispensationDetailDTO update(@RequestBody final DispensationDetailDTO dispensation) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.save(dispensation);
	}

	@ApiOperation("Borrar un detalle de una dispensacion por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		log.debug(CALLING_SERVICE);
		dispensationDetailService.deleteById(id);
	}

	@ApiOperation("Recuperar todas los detalles de las dispensaciones")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<DispensationDetailDTO> findAll(
			@ApiParam(value = "identificador de la dispensacion", required = true) @RequestParam final Long dsp,
			@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.findAllByDispensation(dsp, pageable);
	}

	@ApiOperation("Buscador de detalles de una dispensacion")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/searches")
	public Page<DispensationDetailDTO> findDispensationDetailBySearch(
			@ApiParam(value = "buscador")
			@RequestParam(value = "search", required = false, defaultValue = "") final String search,
			@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.findDispensationDetailsBySearch(search, pageable);
	}

	@ApiOperation("Filtrado de detalles de una dispensacion")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/filters")
	public Page<DispensationDetailDTO> filterDispensations(
			@ApiParam(value = "filtrado")
			@RequestParam(value = "dispensation", required = false, defaultValue = "{}") final String dispensation,
			@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.filterDispensationDetails(dispensation, pageable);
	}

}
