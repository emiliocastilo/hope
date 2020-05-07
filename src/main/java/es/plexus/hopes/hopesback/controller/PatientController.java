package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.service.PatientService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Api(value = "Controlador de pacientes", tags = "patients")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(PatientController.PATIENT_MAPPING)
public class PatientController {

	static final String PATIENT_MAPPING = "/patients";
	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientService patientService;

	@ApiOperation("Recuperar todos los pacientes")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<PatientDTO> findAll(@ApiParam(value = "identificador patologia", required = true) @RequestParam final Long pth,
									@PageableDefault(size = 5) final Pageable pageable) {
		return patientService.findPatientsByPathology(pth, pageable);
	}

	@ApiOperation("Recuperar un paciente por el identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public PatientDTO findById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		Optional<PatientDTO> patient = patientService.findById(id);
		if (!patient.isPresent()) {
			log.error("Id " + id + " is not existed");
			return null;
		}

		return patient.get();
	}

	@ApiOperation("Buscador de pacientes")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/searches")
	public Page<PatientDTO> findPatientBySearch(
			@ApiParam(value = "identificador patologia", required = true) @RequestParam(value = "pth") final Long pth,
			@ApiParam(value = "buscador") @RequestParam(value = "search", required = false, defaultValue = "") final String search,
			@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientService.findPatientBySearch(pth, search, pageable);

	}

	@ApiOperation("Crear un nuevo paciente")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PatientDTO create(@RequestBody @Valid final PatientDTO patient) throws ServiceException {
		return patientService.save(patient);
	}

	//ToDo controlar la exception
	@ApiOperation("Modificar un paciente")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public PatientDTO update(@RequestBody final PatientDTO patient) throws ServiceException {
		if (!patientService.findById(patient.getId()).isPresent()) {
			log.error("Id " + patient.getId() + " is not existed");
			return null;
		}

		return patientService.save(patient);
	}

	//ToDo controlar la exception
	@ApiOperation("Eliminar un paciente")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		if (!patientService.findById(id).isPresent()) {
			log.error("Id " + id + " is not existed");
		}

		patientService.deleteById(id);
	}

	@ApiOperation("Filtrado de pacientes")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/filters")
	public Page<PatientDTO> filterPatients(
			@RequestParam(value = "patient", required = false, defaultValue = "{}") final String patient,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientService.filterPatients(patient, pageable);
	}


}
