package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.service.DispensationDetailService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dispensation-details")
public class DispensationDetailController {
	private static final Logger LOGGER = LogManager.getLogger(DispensationDetailController.class);
	private static final String CALLING_SERVICE = "Calling service...";
	
	private final DispensationDetailService dispensationDetailService;

	@PostMapping
	public ResponseEntity create(@RequestBody DispensationDetailDTO dispensation) {
		LOGGER.debug(CALLING_SERVICE);
		return ResponseEntity.ok(dispensationDetailService.save(dispensation));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DispensationDetailDTO> findById(@PathVariable Long id) {
		LOGGER.debug(CALLING_SERVICE);
		Optional<DispensationDetailDTO> dispensation = dispensationDetailService.findById(id);
		if (!dispensation.isPresent()) {
			LOGGER.error("Id " + id + " is not existed");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(dispensation.get());
	}

	@PutMapping
	public ResponseEntity<DispensationDetailDTO> update(@RequestBody DispensationDetailDTO dispensation) {
		LOGGER.debug(CALLING_SERVICE);
		if (!dispensationDetailService.findById(dispensation.getId()).isPresent()) {
			LOGGER.error("Id " + dispensation.getId() + " is not existed");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(dispensationDetailService.save(dispensation));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		LOGGER.debug(CALLING_SERVICE);
		if (!dispensationDetailService.findById(id).isPresent()) {
			LOGGER.error("Id " + id + " is not existed");
			return ResponseEntity.badRequest().build();
		}
		dispensationDetailService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public Page<DispensationDetailDTO> findAll(@RequestParam Long dsp, @PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return dispensationDetailService.findAllByDispensation(dsp, pageable);
	}

	@GetMapping("/findBySearch")
	public Page<DispensationDetailDTO> findDispensationDetailtBySearch(@RequestParam(value = "search", required = false, defaultValue = "")String search, @PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return dispensationDetailService.findDispensationDetailsBySearch(search, pageable);
	}

	@GetMapping("/filters")
	public Page<DispensationDetailDTO> filterDispensations(@RequestParam(value = "dispensation", required = false, defaultValue = "{}") String dispensation, @PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return dispensationDetailService.filterDispensationDetails(dispensation, pageable);
	}

}
