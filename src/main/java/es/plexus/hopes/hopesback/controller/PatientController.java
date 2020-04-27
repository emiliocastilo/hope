package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.service.PatientService;
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
@RequestMapping("/patient")
public class PatientController {
	private static final Logger LOGGER = LogManager.getLogger(PatientController.class);
	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientService patientService;

	@GetMapping
	public Page<PatientDTO> findAll(@RequestParam Long pth, @PageableDefault(size = 5) Pageable pageable) {
		return patientService.findPatientsByPathology(pth, pageable);
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

	@GetMapping("/findPatientBySearch")
	public Page<PatientDTO> findPatientBySearch(@RequestParam(value = "search", required = false, defaultValue = "") String search, @PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return patientService.findPatientBySearch(search, pageable);

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

	@GetMapping("/filterPatiens")
	public Page<PatientDTO> filterPatiens(@RequestParam(value = "patient", required = false, defaultValue = "{}") String patient, @PageableDefault(size = 5) Pageable pageable) {
		LOGGER.debug(CALLING_SERVICE);
		return patientService.filterPatiens(patient, pageable);
	}


}
