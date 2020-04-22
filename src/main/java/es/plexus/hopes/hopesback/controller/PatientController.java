package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
	private static final Logger LOGGER = LogManager.getLogger(PatientController.class);

	private final PatientService patientService;

	@GetMapping
	public ResponseEntity<List<PatientDTO>> findAll(@RequestParam Long pth) {
		return ResponseEntity.ok(patientService.findPatientsByPathology(pth));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PatientDTO> findById(@PathVariable Long id) {
		Optional<PatientDTO> patient = patientService.findById(id);
		if (!patient.isPresent()) {
			LOGGER.error("Id " + id + " is not existed");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(patient.get());
	}

	//todo añadir los @valid cuando tengamos el crud de hospitales
	@PostMapping
	public ResponseEntity create(@RequestBody PatientDTO patient) {
		return ResponseEntity.ok(patientService.save(patient));
	}

	@PutMapping
	public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patient) {
		if (!patientService.findById(patient.getId()).isPresent()) {
			LOGGER.error("Id " + patient.getId() + " is not existed");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(patientService.save(patient));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		if (!patientService.findById(id).isPresent()) {
			LOGGER.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		patientService.deleteById(id);

		return ResponseEntity.ok().build();
	}

}
