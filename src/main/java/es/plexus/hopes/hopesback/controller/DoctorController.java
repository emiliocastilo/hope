package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.service.DoctorService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	private static final Logger LOGGER = LogManager.getLogger(DoctorController.class);
	private static final String CALLING_SERVICE = "Calling service...";

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

	//todo añadir los @valid en el post y put cuando tengamos en el front los servicios cargados
	@PostMapping
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody final DoctorDTO doctorDTO) {
		LOGGER.debug(CALLING_SERVICE);
		final DoctorDTO doctor = doctorService.addDoctor(doctorDTO);
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(doctorDTO.getId())
				.toUri();

		return ResponseEntity.created(location).body(doctor);
	}

	@PutMapping
	public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody final DoctorDTO doctorDTO) {
		final Long id = doctorDTO.getId();
		if (checkDoctorExistence(id)) return ResponseEntity.badRequest().build();

		LOGGER.debug(CALLING_SERVICE);
		return ResponseEntity.ok(doctorService.updateDoctor(doctorDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDoctor(@PathVariable final Long id) {
		if (checkDoctorExistence(id)) return ResponseEntity.badRequest().build();
		LOGGER.debug(CALLING_SERVICE);
		doctorService.deleteDoctor(id);

		return ResponseEntity.ok().build();
	}

	private boolean checkDoctorExistence(Long id) {
		if (Objects.isNull(doctorService.getOneDoctor(id))) {
			LOGGER.error("Not found doctor with id=" + id);
			return true;
		}
		return false;
	}
}
