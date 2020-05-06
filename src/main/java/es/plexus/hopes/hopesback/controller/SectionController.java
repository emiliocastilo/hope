package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.SectionDTO;
import es.plexus.hopes.hopesback.service.SectionService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(SectionController.MAPPING_SECTIONS)
public class SectionController {
	private static final String CALLING_SERVICE = "Calling service...";

	public static final String MAPPING_SECTIONS = "/sections";

	private final SectionService sectionService;

	@PostMapping
	public SectionDTO create(@RequestBody SectionDTO sectionDto){
		log.debug(CALLING_SERVICE);
		return sectionService.save(sectionDto);
	}

	@GetMapping("/{id}")
	public SectionDTO findById(@PathVariable Long id) {
		log.debug(CALLING_SERVICE);
		return sectionService.findById(id);
	}

	@PutMapping
	public SectionDTO update(@RequestBody SectionDTO sectionDto){
		log.debug(CALLING_SERVICE);
		return sectionService.save(sectionDto);
	}

	@DeleteMapping("/{id}")
	public SectionDTO delete(@PathVariable Long id) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return sectionService.deleteById(id);
	}

	@GetMapping
	public MenuDTO findAll() {
		log.debug(CALLING_SERVICE);
		return sectionService.findAllSections();
	}

}
