package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.service.PatientDiagnosisService;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import es.plexus.hopes.hopesback.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(PatientDiagnosisController.PATIENT_DIAGNOSE_MAPPING)
public class PatientDiagnosisController {
	static final String PATIENT_DIAGNOSE_MAPPING = "/patients-diagnoses";
	static final String PATIENT_DIAGNOSE_INDICATIONS = "/indications";
	static final String PATIENT_DIAGNOSE_CIE = "/cie";
	static final String PATIENT_DIAGNOSE_TREATMENT = "/treatments";
	static final String PATIENT_DIAGNOSE_COMBINED_TREATMENT = "/combined-treatments";
	static final String PATIENT_DIAGNOSE_END_CAUSE = "/end-causes";
	static final String PATIENT_DIAGNOSE_END_CAUSE_LAST_YEARS = "/end-causes-last-years";
	static final String PATIENT_DIAGNOSE_NUMBER_CHANGES = "/number-changes";
	static final String PATIENT_DIAGNOSE_INDICATIONS_PATIENTS = "/indications/patients";
	static final String PATIENT_DIAGNOSE_CIE_PATIENTS = "/cie/patients";
	static final String PATIENT_DIAGNOSE_INDICATIONS_PATIENTS_EXPORT = "/indications/patients-export";
	static final String PATIENT_DIAGNOSE_CIE_PATIENTS_EXPORT = "/cie/patients-export";
	static final String PATIENT_DIAGNOSE_TREATMENT_PATIENTS = "/treatments/patients";
	static final String PATIENT_DIAGNOSE_TREATMENT_PATIENTS_EXPORT = "/treatments/patients-export";
	static final String PATIENT_DIAGNOSE_COMBINED_TREATMENT_PATIENTS = "/combined-treatments/patients";
	static final String PATIENT_DIAGNOSE_COMBINED_TREATMENT_PATIENTS_EXPORT = "/combined-treatments/patients-export";
	static final String PATIENT_DIAGNOSE_PATIENTS_END_CAUSES = "/end-causes/patients";
	static final String PATIENT_DIAGNOSE_PATIENTS_END_CAUSES_EXPORT = "/end-causes/patients-export";
	static final String PATIENT_DIAGNOSE_PATIENTS_END_CAUSES_LAST_YEARS = "/end-causes-last-years/patients";
	static final String PATIENT_DIAGNOSE_PATIENTS_END_CAUSES_LAST_YEARS_EXPORT = "/end-causes-last-years/patients-export";
	static final String PATIENT_DIAGNOSE_PATIENTS_NUMBER_CHANGES = "/number-changes/patients";
	static final String PATIENT_DIAGNOSE_PATIENTS_NUMBER_CHANGES_EXPORT = "/number-changes/patients-export";
	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientDiagnosisService patientDiagnosisService;
	private final PatientTreatmentService patientTreatmentService;
	private final RoleService roleService;

	@ApiOperation("Gráfico/Tabla de Pacientes por Indicacion - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_INDICATIONS)
	public Map<String, Map<Boolean, Integer>> findPatientsDiagnosesByIndications(
			@RequestHeader(name = "Authorization") final String token) {

		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findPatientsByIndicationAndPathology(roleService.getPathologyByRoleSelected(token));
	}

	@ApiOperation("Gráfico/Tabla de Pacientes por diagnóstico CIE - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CIE)
	public Map<String, Long> findPatientsDiagnosesByCie(
			@ApiParam(value = "Identificador del hospital de la sesión", example = "1", required = true)
			@RequestParam(value = "hospitalId") final Long hospitalId,
			@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findPatientsByCieAndPathology(hospitalId, roleService.getPathologyByRoleSelected(token));
	}

	@ApiOperation("Gráfico/Tabla de Pacientes por Tipo de Tratamiento - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_TREATMENT)
	public Map<String, Long> findPatientTreatmentByTreatment(@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByTreatmentAndPathology(roleService.getPathologyByRoleSelected(token));
	}

	@ApiOperation("Gráfico/Tabla de Pacientes por tratamientos combinados - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_COMBINED_TREATMENT)
	public Map<String, Long> findPatientTreatmentByCombinedTreatment(@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByCombinedTreatmentAndPathology(roleService.getPathologyByRoleSelected(token));
	}

	@ApiOperation("Gráfico/Tabla de motivo del último cambio/suspensión del tratamiento biológico - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_END_CAUSE)
	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatment(
			@ApiParam(value = "Causa por la que filtrar", example = "Cambio", required = true)
			@RequestParam(value = "endCause") String endCause,
			@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathology(endCause,roleService.getPathologyByRoleSelected(token));
	}

	@ApiOperation("Gráfico/Tabla de motivo del último cambio/suspensión del tratamiento biológico de los últimos 5 años - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_END_CAUSE_LAST_YEARS)
	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(
			@ApiParam(value = "Causa por la que filtrar", example = "Cambio", required = true)
			@RequestParam(value = "endCause") String endCause,
			@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathologyInLast5Years(endCause, roleService.getPathologyByRoleSelected(token));
	}

	@ApiOperation("Gráfico/Tabla de número de cambios de tratamientos biológicos - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_NUMBER_CHANGES)
	public Map<Long, Integer> findPatientTreatmentByNumberChangesOfBiologicTreatment(
			@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment(roleService.getPathologyByRoleSelected(token));
	}

	@ApiOperation("Listado de pacientes por indicación - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_INDICATIONS_PATIENTS)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByIndication(
			@ApiParam(value = "Indicación por la que filtrar", example = "Dermtitis", required = true)
			@RequestParam final String indication,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findGraphPatientsDetailsByIndication(indication, pageable);
	}

	@ApiOperation("Listado de pacientes por indicación - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_INDICATIONS_PATIENTS_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByIndication(
			@ApiParam(value = "Indicación por la que filtrar", example = "Dermtitis", required = true)
			@RequestParam final String indication) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findGraphPatientsDetailsByIndication(indication);
	}

	@ApiOperation("Listado de pacientes por cie del hospital - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CIE_PATIENTS)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCie(
			@ApiParam(value = "Descripción del CIE por la que filtrar", example = "Dermtitis", required = true)
			@RequestParam final String cieDescription,
			@ApiParam(value = "Identificador del hospital de la sesión", example = "1", required = true)
			@RequestParam(value = "hospitalId") final Long hospitalId,
			@PageableDefault(size = 5) Pageable page) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findGraphPatientsDetailsByCie(cieDescription, hospitalId, page);
	}

	@ApiOperation("Listado de pacientes por cie - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CIE_PATIENTS_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCie(
			@ApiParam(value = "Descripción del CIE por la que filtrar", example = "Dermtitis", required = true)
			@RequestParam final String cieDescription,
			@ApiParam(value = "Identificador del hospital de la sesión", example = "1", required = true)
			@RequestParam(value = "hospitalId") final Long hospitalId) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findGraphPatientsDetailsByCie(cieDescription, hospitalId);
	}

	@ApiOperation("Listado de pacientes por Tipo de tratamiento - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_TREATMENT_PATIENTS)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByTypeTreatment(
			@ApiParam(value = "Descripción del tipo de tratamiento por la que filtrar", example = "Combinado", required = true)
			@RequestParam final String treatmentType,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByTypeTreatment(treatmentType, pageable);
	}

	@ApiOperation("Listado de pacientes por Tipo de tratamiento - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_TREATMENT_PATIENTS_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByTypeTreatment(
			@ApiParam(value = "Descripción del tipo de tratamiento por la que filtrar", example = "Combinado", required = true)
			@RequestParam final String treatmentType) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByTypeTreatment(treatmentType);
	}

	//TO-DO ver la query
	@ApiOperation("Listado de pacientes por combinación de tratamiento combinado - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_COMBINED_TREATMENT_PATIENTS)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCombinedTreatment(
			@ApiParam(value = "Combinación del tratamiento por la que filtrar", example = "Quimico-Biologico", required = true)
			@RequestParam final String combinedTreatment,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByCombiendTreatment(combinedTreatment, pageable);
	}

	//TO-DO ver la query
	@ApiOperation("Listado de pacientes por combinación de tratamiento combinado - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_COMBINED_TREATMENT_PATIENTS_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCombinedTreatment(
			@ApiParam(value = "Combinación del tratamiento por la que filtrar", example = "Quimico-Biologico", required = true)
			@RequestParam final String combinedTreatment) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByCombiendTreatment(combinedTreatment);
	}

	@ApiOperation("Listado de pacientes por Tipo de tratamiento combinado - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_PATIENTS_END_CAUSES)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatment(
			@ApiParam(value = "Causa de finalización del tratamiento por la que filtrar", example = "Cambio/Suspensión", required = true)
			@RequestParam final String endCause,
			@ApiParam(value = "Razón de la causa de finalización del tratamiento por la que filtrar", example = "Ineficacia", required = true)
			@RequestParam final String reason,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatment(endCause, reason, pageable);
	}

	@ApiOperation("Listado de pacientes por Tipo de tratamiento combinado - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_PATIENTS_END_CAUSES_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatment(
			@ApiParam(value = "Causa de finalización del tratamiento por la que filtrar", example = "Cambio/Suspensión", required = true)
			@RequestParam final String endCause,
			@ApiParam(value = "Razón de la causa de finalización del tratamiento por la que filtrar", example = "Ineficacia", required = true)
			@RequestParam final String reason) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatment(endCause, reason);
	}

	@ApiOperation("Listado de pacientes por causa de finalización del tratamiento biológico en los ultimos años - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_PATIENTS_END_CAUSES_LAST_YEARS)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears(
			@ApiParam(value = "Causa de finalización del tratamiento por la que filtrar", example = "Cambio/Suspensión", required = true)
			@RequestParam final String endCause,
			@ApiParam(value = "Razón de la causa de finalización del tratamiento por la que filtrar", example = "Ineficacia", required = true)
			@RequestParam final String reason,
			@ApiParam(value = "Últimos años que consultar ", example = "3", required = true)
			@RequestParam final int years,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears(endCause, reason, years, pageable);
	}

	@ApiOperation("Listado de pacientes por causa de finalización del tratamiento biológico en los ultimos años - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_PATIENTS_END_CAUSES_LAST_YEARS_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears(
			@ApiParam(value = "Causa de finalización del tratamiento por la que filtrar", example = "Cambio/Suspensión", required = true)
			@RequestParam final String endCause,
			@ApiParam(value = "Razón de la causa de finalización del tratamiento por la que filtrar", example = "Ineficacia", required = true)
			@RequestParam final String reason,
			@ApiParam(value = "Últimos años que consultar ", example = "3", required = true)
			@RequestParam final int years){
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears(endCause, reason, years);
	}

	@ApiOperation("Listado páginado de pacientes por número de cambios de tratamiento biológico - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_PATIENTS_NUMBER_CHANGES)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByNumberChanges(
			@ApiParam(value = "Últimos años que consultar ", example = "3", required = true)
			@RequestParam final int numberChanges,
			@PageableDefault(size = 5) Pageable pageable,
			@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByNumberChanges(numberChanges, pageable, roleService.getPathologyByRoleSelected(token));
	}

	@ApiOperation("Listado páginado de pacientes por número de cambios de tratamiento biológico - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_PATIENTS_NUMBER_CHANGES_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByNumberChanges(
			@ApiParam(value = "Causa de finalización del tratamiento por la que filtrar", example = "Cambio/Suspensión", required = true)
			@RequestParam final int numberChanges,
			@RequestHeader(name = "Authorization") final String token){
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByNumberChanges(numberChanges, roleService.getPathologyByRoleSelected(token));
	}
}