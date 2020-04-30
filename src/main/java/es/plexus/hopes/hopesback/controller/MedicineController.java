package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MedicineDTO;
import es.plexus.hopes.hopesback.service.MedicineService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
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
@RequestMapping(MedicineController.MAPPING_MEDICINES)
public class MedicineController {
	private static final String CALLING_SERVICE = "Calling service...";

	public static final String MAPPING_MEDICINES = "/medicines";

	private final MedicineService medicineService;

	@PostMapping
	public MedicineDTO create(@RequestBody MedicineDTO medicineDto) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return medicineService.save(medicineDto);
	}

	@GetMapping("/{id}")
	public MedicineDTO findById(@PathVariable Long id) {
		log.debug(CALLING_SERVICE);
		return medicineService.findById(id);
	}

	@PutMapping
	public MedicineDTO update(@RequestBody MedicineDTO medicineDto) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return medicineService.save(medicineDto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		log.debug(CALLING_SERVICE);
		medicineService.deleteById(id);
	}

	@GetMapping
	public Page<MedicineDTO> findAll(@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return medicineService.findAllMedicines(pageable);
	}

	@GetMapping("/findBySearch")
	public Page<MedicineDTO> findBySearch(@RequestParam(value = "search", required = false, defaultValue = "")
													  String search, @PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return medicineService.findMedicinesBySearch(search, pageable);
	}

	@GetMapping("/filters")
	public Page<MedicineDTO> filters(@RequestParam(value = "medicines", required = false, defaultValue = "{}")
												 String medicines, @PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return medicineService.filterMedicines(medicines, pageable);
	}

}
