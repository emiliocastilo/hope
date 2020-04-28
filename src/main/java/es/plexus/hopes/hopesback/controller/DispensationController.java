package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DispensationDTO;
import es.plexus.hopes.hopesback.controller.model.FormDispensationDTO;
import es.plexus.hopes.hopesback.service.DispensationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dispensation")
public class DispensationController {
	private static final Logger LOGGER = LogManager.getLogger(DispensationController.class);
	private static final String CALLING_SERVICE = "Calling service...";
	
	private final DispensationService dispensationService;

	@PostMapping
	public DispensationDTO create(@ModelAttribute FormDispensationDTO dispensation) {
		LOGGER.debug(CALLING_SERVICE);
		return dispensationService.save(dispensation);
	}

	@GetMapping("/{id}")
	public DispensationDTO findById(@PathVariable Long id) {
		LOGGER.debug(CALLING_SERVICE);
		return dispensationService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		LOGGER.debug(CALLING_SERVICE);
		dispensationService.deleteById(id);
	}

	@GetMapping
	public Page<DispensationDTO> findAll(@PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return dispensationService.findAll(pageable);
	}

}
