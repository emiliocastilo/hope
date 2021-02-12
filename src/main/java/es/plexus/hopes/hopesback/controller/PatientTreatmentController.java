package es.plexus.hopes.hopesback.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.controller.model.MedicineDosis;
import es.plexus.hopes.hopesback.controller.model.PatientTreatmentDTO;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import es.plexus.hopes.hopesback.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "Controlador de Patients Treatments", tags = "patients-treatments")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/patients-treatments")
public class PatientTreatmentController {
	
	static final String FIND_PATIENTS_UDER_TREATMENT = "/find-patients-under-treatment";
	static final String FIND_INFO_PATIENTS_DOSES = "/find-info-patients-doses";
	static final String FIND_INFO_PATIENTS_DOSES_MEDICINE_DOSIS = "/find-info-patients-doses-medicines";
	static final String GET_DETAIL_PATIENTS_UDER_TREATMENT = "/get-detail-patients-under-treatment";
	static final String GET_DETAIL_PATIENTS_UDER_TREATMENT_EXPORT= "/get-detail-patients-under-treatment-export";
	static final String GET_DETAIL_PATIENTS_PER_DOSES = "/get-detail-patients-per-doses";
	static final String GET_DETAIL_PATIENTS_PER_DOSES_EXPORT = "/get-detail-patients-per-doses-export";
	static final String FIND_ALL_TREATMENTS = "/find-all-treatments";
	static final String FIND_BY_PATIENT = "/find-by-patient";
	static final String DELETE = "/delete";
	static final String SUSPEND = "/suspend";
	static final String CREATE = "/new";
	static final String UPDATE = "/update";

	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientTreatmentService patientTreatmentService;
	private final RoleService roleService;
	
	@ApiOperation("Recuperar los pacientes bajo tratameinto (medicamento)")
	@GetMapping(FIND_PATIENTS_UDER_TREATMENT)
	public Map<String, Long> findPatientsUnderTreatment(@RequestParam(value = "type") String type, @RequestParam(value = "indication", required = false) String indication,
														@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientsUnderTreatment(type, indication,roleService.getPathologyByRoleSelected(token));
	}
	
	@ApiOperation("Recuperar los relacion de dosis - paciente")
	@GetMapping(FIND_INFO_PATIENTS_DOSES)
	public Map<String, Long> findInfoPatientsDoses() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findInfoPatientsDoses();
	}

	@ApiOperation("Recuperar los relacion de dosis - paciente y Medicina")
	@GetMapping(FIND_INFO_PATIENTS_DOSES_MEDICINE_DOSIS)
	public List<MedicineDosis> findInfoPatientsDosesMedicine(@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findInfoPatientsDosesMedicines(roleService.getPathologyByRoleSelected(token));
	}


	@ApiOperation("Detalle de pacientes bajo tratamiento")
	@GetMapping(GET_DETAIL_PATIENTS_UDER_TREATMENT)
	public Page<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(@RequestParam(value = "type") String type, @RequestParam(value = "indication", required = false) String indication,
																	   @RequestParam(value="medicine", required = false) String medicine,@PageableDefault(size = 5) final Pageable pageable,
																	   @RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.getDetailPatientsUnderTreatment(type, indication, medicine, pageable, roleService.getPathologyByRoleSelected(token));
	}
	
	@ApiOperation("Detalle de pacientes bajo tratamiento para exportar")
	@GetMapping(GET_DETAIL_PATIENTS_UDER_TREATMENT_EXPORT)
	public List<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(@RequestParam(value = "type") String type, @RequestParam(value = "indication", required = false) String indication,
																	   @RequestParam(value = "medicine", required = false) String medicine,
																	   @RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.getDetailPatientsUnderTreatment(type, indication, medicine, roleService.getPathologyByRoleSelected(token));
	}
	
	@ApiOperation("Detalle de pacientes/dosis")
	@GetMapping(GET_DETAIL_PATIENTS_PER_DOSES)
	public Page<GraphPatientDetailDTO> getDetailPatientsPerDoses(
			@RequestParam(value = "regimen")String regimen,
			@PageableDefault(size = 5) final Pageable pageable,
			@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.getDetailPatientsPerDoses(regimen, pageable, roleService.getPathologyByRoleSelected(token));
	}
	
	@ApiOperation("Detalle de pacientes/dosis para exportar")
	@GetMapping(GET_DETAIL_PATIENTS_PER_DOSES_EXPORT)
	public List<GraphPatientDetailDTO> getDetailPatientsPerDoses(@RequestParam(value = "regimen")String regimen) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.getDetailPatientsPerDoses(regimen);
	}

	@ApiOperation("Obtener todos los tratamientos")
	@GetMapping(FIND_ALL_TREATMENTS)
	public List<PatientTreatmentDTO> findAll() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findAll();
	}

	@ApiOperation("Obtener todos los tratamientos")
	@GetMapping(FIND_BY_PATIENT)
	public List<PatientTreatmentDTO> findByPatientId(@RequestParam(value = "patientId") Long patientId) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findByPatient(patientId);
	}

	@ApiOperation("Obtener todos los tratamientos")
	@DeleteMapping(DELETE)
	public void delete(@RequestBody(required = true) Long treatmentId) {
		// TODO: Borrar de forma lógica el tratamiento y sus lineas. ( No borrar en base de datos )
	}

	@ApiOperation("Suspende un tratamiento")
	@PostMapping(SUSPEND)
	public void suspend(@RequestBody(required = true) Long treatmentId) {
		// TODO: Suspender la linea activa del tratamiento
	}

	@ApiOperation("Crea un nuevo tratamiento")
	@PostMapping(CREATE)
	public void create(@RequestBody(required = true) PatientTreatmentDTO patientTreatmentDTO) {
		// TODO: Crear en base de datos el nuevo tratamiento.
	}

	@ApiOperation("Actualizar un tratamiento")
	@PostMapping(UPDATE)
	public void update(@RequestBody(required = true) PatientTreatmentDTO patientTreatmentDTO) {
		// TODO: Persistir el objeto con los cambios realizados ( Hay que suspender la linea activa y crear una nueva ).
	}

}
