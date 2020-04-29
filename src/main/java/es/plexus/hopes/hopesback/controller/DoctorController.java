package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.service.DoctorService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	private static final Logger LOGGER = LogManager.getLogger(DoctorController.class);
	private static final String CALLING_SERVICE = "Calling service...";
	private static final String NOT_FOUND_DOCTOR_WITH_ID = "Not found doctor with id=";

	private final DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping
	public ResponseEntity<Page<DoctorDTO>> getAllDoctors(@PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return ResponseEntity.ok(doctorService.getAllDoctors(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DoctorDTO> getOneDoctor(@PathVariable Long id) {
		return ResponseEntity.ok(doctorService.getOneDoctor(id));
	}

	@GetMapping("/findDoctorsBySearch")
	public Page<DoctorDTO> findDoctorsBySearch(@RequestParam(value = "search", required = false, defaultValue = "") String search,
											   @PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return doctorService.findDoctorsBySearch(search, pageable);

	}

	@GetMapping("/filterDoctors")
	public Page<DoctorDTO> filterDoctors(@RequestParam(value = "doctor", required = false, defaultValue = "{}") String doctor,
										 @PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return doctorService.filterDoctors(doctor, pageable);
	}

	@PostMapping
	public DoctorDTO addDoctor(@RequestBody @Valid final DoctorDTO doctorDTO) throws ServiceException {
		LOGGER.debug(CALLING_SERVICE);
		return doctorService.addDoctor(doctorDTO);
	}

	@PutMapping
	public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody @Valid final DoctorDTO doctorDTO) throws ServiceException {
		final Long id = doctorDTO.getId();
		if (checkDoctorExistence(id)) throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
				.exception(NOT_FOUND_DOCTOR_WITH_ID + id);

		LOGGER.debug(CALLING_SERVICE);
		return ResponseEntity.ok(doctorService.updateDoctor(doctorDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDoctor(@PathVariable final Long id) throws ServiceException {
		if (checkDoctorExistence(id)) throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
				.exception(NOT_FOUND_DOCTOR_WITH_ID + id);

		LOGGER.debug(CALLING_SERVICE);
		doctorService.deleteDoctor(id);

		return ResponseEntity.ok().build();
	}

	private boolean checkDoctorExistence(Long id) {
		return Objects.isNull(doctorService.getOneDoctor(id));
	}
}
