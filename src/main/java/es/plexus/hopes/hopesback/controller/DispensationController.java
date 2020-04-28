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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dispensation")
public class DispensationController {
	private static final Logger LOGGER = LogManager.getLogger(DispensationController.class);
	private static final String CALLING_SERVICE = "Calling service...";
	
	private final DispensationService dispensationService;

	@PostMapping
	public ResponseEntity create(@ModelAttribute FormDispensationDTO dispensation) {
		LOGGER.debug(CALLING_SERVICE);
		return ResponseEntity.ok(dispensationService.save(dispensation));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DispensationDTO> findById(@PathVariable Long id) {
		LOGGER.debug(CALLING_SERVICE);
		Optional<DispensationDTO> dispensation = dispensationService.findById(id);
		if (!dispensation.isPresent()) {
			LOGGER.error("Id " + id + " is not existed");
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(dispensation.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		LOGGER.debug(CALLING_SERVICE);
		if (!dispensationService.findById(id).isPresent()) {
			LOGGER.error("Id " + id + " is not existed");
			return ResponseEntity.badRequest().build();
		}
		dispensationService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public Page<DispensationDTO> findAll(@PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return dispensationService.findAll(pageable);
	}

}
