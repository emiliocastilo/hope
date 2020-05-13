package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.SectionDTO;
import es.plexus.hopes.hopesback.service.SectionService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Controlador de sectiones", tags = "sections")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(SectionController.MAPPING_SECTIONS)
public class SectionController {

	static final String MAPPING_SECTIONS = "/sections";
	private static final String CALLING_SERVICE = "Llamando al servicio...";

	private final SectionService sectionService;

	@ApiOperation("Añadir una nueva seccion")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public SectionDTO create(@RequestBody final SectionDTO sectionDto) {
		log.debug(CALLING_SERVICE);
		return sectionService.save(sectionDto);
	}

	@ApiOperation("Buscar una seccion por identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public SectionDTO findById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		log.debug(CALLING_SERVICE);
		return sectionService.findById(id);
	}

	@ApiOperation("Modificar una seccion")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public SectionDTO update(@RequestBody final SectionDTO sectionDto) {
		log.debug(CALLING_SERVICE);
		return sectionService.save(sectionDto);
	}

	@ApiOperation("Eliminar una seccion por identificador")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public SectionDTO delete(@ApiParam(value = "identificador", required = true)
							 @PathVariable final Long id) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return sectionService.deleteById(id);
	}

	@ApiOperation("Recuperar todas las seccioens")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public MenuDTO findAll() {
		log.debug(CALLING_SERVICE);
		return sectionService.findAllSections();
	}

}
