package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.service.DoctorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	private static final Logger LOGGER = LogManager.getLogger(DoctorController.class);

	private final DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping
	public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
		LOGGER.debug("Calling service...");
		return ResponseEntity.ok(doctorService.getAllDoctors());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DoctorDTO> getOneDoctor(@PathVariable Long id) {
		return ResponseEntity.ok(doctorService.getOneDoctor(id));
	}

	@PostMapping
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody @Valid final DoctorDTO doctorDTO) {
		LOGGER.debug("Calling service...");
		final DoctorDTO doctor = doctorService.addDoctor(doctorDTO);
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(doctorDTO.getId())
				.toUri();

		return ResponseEntity.created(location).body(doctor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody @Valid final DoctorDTO doctorDTO) {
		doctorDTO.setId(id);
		LOGGER.debug("Calling service...");
		return ResponseEntity.ok(doctorService.updateDoctor(doctorDTO));
	}

	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable Long id) {
		LOGGER.debug("Calling service...");
		doctorService.deleteDoctor(id);
	}

}
