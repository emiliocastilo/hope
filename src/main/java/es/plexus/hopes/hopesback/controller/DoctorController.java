package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.service.DoctorService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;

@Api(value = "Controlador de medicos", tags = "doctor")
@Log4j2
@RestController
@RequestMapping(DoctorController.DOCTOR_MAPPING)
public class DoctorController {

	static final String DOCTOR_MAPPING = "/doctors";
	private static final String CALLING_SERVICE = "Calling service...";
	private static final String NOT_FOUND_DOCTOR_WITH_ID = "Not found doctor with id=";

	private final DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@ApiOperation("Recuperar todos los doctores")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<DoctorDTO> getAllDoctors(@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return doctorService.getAllDoctors(pageable);
	}

	@ApiOperation("Buscar un doctor por el identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public DoctorDTO getOneDoctor(
			@ApiParam(value = "identificador", required = true) @PathVariable Long id) {
		return doctorService.getOneDoctor(id);
	}

	@ApiOperation("Buscador de medicos")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/searches")
	public Page<DoctorDTO> findDoctorsBySearch(
			@ApiParam(value = "buscador")
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return doctorService.findDoctorsBySearch(search, pageable);

	}

	@ApiOperation("Filtro de medicos")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/filters")
	public Page<DoctorDTO> filterDoctors(
			@ApiParam(value = "filtrado")
			@RequestParam(value = "doctor", required = false, defaultValue = "{}") String doctor,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return doctorService.filterDoctors(doctor, pageable);
	}

	@ApiOperation("Añadir un medico nuevo")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public DoctorDTO addDoctor(@RequestBody @Valid final DoctorDTO doctorDTO) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return doctorService.addDoctor(doctorDTO);
	}

	@ApiOperation("Actualizar un medico")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public DoctorDTO updateDoctor(@RequestBody @Valid final DoctorDTO doctorDTO) throws ServiceException {
		final Long id = doctorDTO.getId();
		if (checkDoctorExistence(id)) throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
				.exception(NOT_FOUND_DOCTOR_WITH_ID + id);

		log.debug(CALLING_SERVICE);
		return doctorService.updateDoctor(doctorDTO);
	}

	@ApiOperation("Borrar un medico por identificador")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteDoctor(
			@ApiParam(value = "identificador") @PathVariable final Long id) throws ServiceException {
		if (checkDoctorExistence(id)) throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
				.exception(NOT_FOUND_DOCTOR_WITH_ID + id);

		log.debug(CALLING_SERVICE);
		doctorService.deleteDoctor(id);
	}

	private boolean checkDoctorExistence(Long id) {
		return Objects.isNull(doctorService.getOneDoctor(id));
	}
}
