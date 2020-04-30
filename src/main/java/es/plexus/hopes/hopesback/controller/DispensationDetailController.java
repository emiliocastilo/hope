package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.service.DispensationDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/dispensation-details")
public class DispensationDetailController {
	private static final String CALLING_SERVICE = "Calling service...";
	
	private final DispensationDetailService dispensationDetailService;

	@PostMapping
	public DispensationDetailDTO create(@RequestBody DispensationDetailDTO dispensation) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.save(dispensation);
	}

	@GetMapping("/{id}")
	public DispensationDetailDTO findById(@PathVariable Long id) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.findById(id);
	}

	@PutMapping
	public DispensationDetailDTO update(@RequestBody DispensationDetailDTO dispensation) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.save(dispensation);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		log.debug(CALLING_SERVICE);
		dispensationDetailService.deleteById(id);
	}

	@GetMapping
	public Page<DispensationDetailDTO> findAll(@RequestParam Long dsp, @PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.findAllByDispensation(dsp, pageable);
	}

	@GetMapping("/findBySearch")
	public Page<DispensationDetailDTO> findDispensationDetailtBySearch(@RequestParam(value = "search", required = false, defaultValue = "")String search, @PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.findDispensationDetailsBySearch(search, pageable);
	}

	@GetMapping("/filters")
	public Page<DispensationDetailDTO> filterDispensations(@RequestParam(value = "dispensation", required = false, defaultValue = "{}") String dispensation, @PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return dispensationDetailService.filterDispensationDetails(dispensation, pageable);
	}

}
