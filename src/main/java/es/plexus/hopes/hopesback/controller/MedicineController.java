package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DoseDTO;
import es.plexus.hopes.hopesback.controller.model.MedicineDTO;
import es.plexus.hopes.hopesback.service.MedicineService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value = "Controlador de medicamentos", tags = "medicine")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(MedicineController.MAPPING_MEDICINES)
public class MedicineController {

	static final String MAPPING_MEDICINES = "/medicines";
	private static final String CALLING_SERVICE = "Llamando al servicio...";

	private final MedicineService medicineService;

	@ApiOperation("Crear un nuevo medicamento")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MedicineDTO create(@RequestBody final MedicineDTO medicineDto) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return medicineService.save(medicineDto);
	}

	@ApiOperation("Añadir nuevos medicamentos desde un fichero Excel")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/createAll")
	public void createAll(@ModelAttribute final MultipartFile multipartFile) {
		log.debug(CALLING_SERVICE);
		medicineService.saveAll(multipartFile);
	}

	@ApiOperation("Buscar un medicamento por un identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public MedicineDTO findById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		log.debug(CALLING_SERVICE);
		return medicineService.findById(id);
	}

	@ApiOperation("Actualizar un medicamento")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public MedicineDTO update(@RequestBody final MedicineDTO medicineDto) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return medicineService.save(medicineDto);
	}

	@ApiOperation("Borrar un medicamento por el identificador")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		log.debug(CALLING_SERVICE);
		medicineService.deleteById(id);
	}

	@ApiOperation("Recuperar todos los medicamentos")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<MedicineDTO> findAll(@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return medicineService.findAllMedicines(pageable);
	}

	@ApiOperation("Buscador de medicamentos")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/searches")
	public Page<MedicineDTO> findBySearch(
			@ApiParam(value = "buscador")
			@RequestParam(value = "search", required = false, defaultValue = "") final String search,
			@ApiParam(value = "treatmentType")
			@RequestParam(value = "treatmentType", required = false) final String treatmentType,
			@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return medicineService.findMedicinesBySearchOrSearchAndTreatmentType(search, treatmentType, pageable);
	}

	@ApiOperation("Filtrado de medicamentos")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/filters")
	public Page<MedicineDTO> filters(
			@ApiParam(value = "filtrado")
			@RequestParam(value = "medicines", required = false, defaultValue = "{}") final String medicines,
			@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return medicineService.filterMedicines(medicines, pageable);
	}

	@ApiOperation("Buscar dosis a partir de un identificador de medicamento")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/doses")
	public List<DoseDTO> findDosesByMedicineId(@ApiParam(value = "identificador", required = true) @RequestParam(value = "medicineId", defaultValue = "1") final Long medicineId) {
		log.debug(CALLING_SERVICE);
		return medicineService.findDosesByMedicineId(medicineId);
	}

}
