package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.service.PatientDiagnosisService;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(PatientDiagnosisController.PATIENT_DIAGNOSE_MAPPING)
public class PatientDiagnosisController {
	static final String PATIENT_DIAGNOSE_MAPPING = "/patients-diagnoses";
	static final String PATIENT_DIAGNOSE_INDICATIONS = "/indications";
	static final String PATIENT_DIAGNOSE_CI9 = "/cie9";
	static final String PATIENT_DIAGNOSE_CI10 = "/cie10";
	static final String PATIENT_DIAGNOSE_TREATMENT = "/treatments";
	static final String PATIENT_DIAGNOSE_COMBINED_TREATMENT = "/combined-treatments";
	static final String PATIENT_DIAGNOSE_END_CAUSE = "/end-causes";
	static final String PATIENT_DIAGNOSE_END_CAUSE_LAST_YEARS = "/end-causes-last-years";
	static final String PATIENT_DIAGNOSE_NUMBER_CHANGES = "/number-changes";
	static final String PATIENT_DIAGNOSE_INDICATIONS_PATIENTS = "/indications/patients";
	static final String PATIENT_DIAGNOSE_CI9_PATIENTS = "/cie9/patients";
	static final String PATIENT_DIAGNOSE_CI10_PATIENTS = "/cie10/patients";
	static final String PATIENT_DIAGNOSE_INDICATIONS_PATIENTS_EXPORT = "/indications/patients-export";
	static final String PATIENT_DIAGNOSE_CI9_PATIENTS_EXPORT = "/cie9/patients-export";
	static final String PATIENT_DIAGNOSE_CI10_PATIENTS_EXPORT = "/cie10/patients-export";
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

	@ApiOperation("Gráfico/Tabla de Pacientes por Indicacion - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_INDICATIONS)
	public Map<String, Map<Boolean, Integer>> findPatientsDiagnosesByIndications() {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findPatientsByIndication();
	}

	@ApiOperation("Gráfico/Tabla de Pacientes por diagnóstico CIE9 - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CI9)
	public Map<String, Long> findPatientsDiagnosesByCie9(){
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findPatientsByCie9();
	}

	@ApiOperation("Gráfico/Tabla de Pacientes por diagnóstico CIE10 - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CI10)
	public Map<String, Long> findPatientsDiagnosesByCie10(){
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findPatientsByCie10();
	}

	@ApiOperation("Gráfico/Tabla de Pacientes por Tipo de Tratamiento - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_TREATMENT)
	public Map<String, Long> findPatientTreatmentByTreatment() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByTreatment();
	}

	@ApiOperation("Gráfico/Tabla de Pacientes por tratamientos combinados - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_COMBINED_TREATMENT)
	public Map<String, Long> findPatientTreatmentByCombinedTreatment() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByCombinedTreatment();
	}

	@ApiOperation("Gráfico/Tabla de motivo del último cambio/suspensión del tratamiento biológico - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_END_CAUSE)
	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatment(
			@ApiParam(value = "Causa por la que filtrar", example = "Cambio", required = true)
			@RequestParam(value = "endCause") String endCause) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatment(endCause);
	}

	@ApiOperation("Gráfico/Tabla de motivo del último cambio/suspensión del tratamiento biológico de los últimos 5 años - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_END_CAUSE_LAST_YEARS)
	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(
			@ApiParam(value = "Causa por la que filtrar", example = "Cambio", required = true)
			@RequestParam(value = "endCause") String endCause) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(endCause);
	}

	@ApiOperation("Gráfico/Tabla de número de cambios de tratamientos biológicos - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_NUMBER_CHANGES)
	public Map<Long, Integer> findPatientTreatmentByNumberChangesOfBiologicTreatment() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment();
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

	@ApiOperation("Listado de pacientes por cie9 - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CI9_PATIENTS)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCie9(
			@ApiParam(value = "Descripción del CIE9 por la que filtrar", example = "Dermtitis" , required = true)
			@RequestParam final String cie9,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findGraphPatientsDetailsByCie9(cie9, pageable);
	}

	@ApiOperation("Listado de pacientes por cie9 - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CI9_PATIENTS_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCie9(
			@ApiParam(value = "Descripción del CIE9 por la que filtrar", example = "Dermtitis" , required = true)
			@RequestParam final String cie9) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findGraphPatientsDetailsByCie9(cie9);
	}

	@ApiOperation("Listado de pacientes por cie10 - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CI10_PATIENTS)
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCie10(
			@ApiParam(value = "Descripción del CIE10 por la que filtrar", example = "Dermtitis", required = true)
			@RequestParam final String cie10,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findGraphPatientsDetailsByCie10(cie10, pageable);
	}

	@ApiOperation("Listado de pacientes por cie10 - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_CI10_PATIENTS_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCie10(
			@ApiParam(value = "Descripción del CIE10 por la que filtrar", example = "Dermtitis", required = true)
			@RequestParam final String cie10) {
		log.debug(CALLING_SERVICE);
		return patientDiagnosisService.findGraphPatientsDetailsByCie10(cie10);
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
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByNumberChanges(numberChanges, pageable);
	}

	@ApiOperation("Listado páginado de pacientes por número de cambios de tratamiento biológico - Información Diagnóstico")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(PATIENT_DIAGNOSE_PATIENTS_NUMBER_CHANGES_EXPORT)
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByNumberChanges(
			@ApiParam(value = "Causa de finalización del tratamiento por la que filtrar", example = "Cambio/Suspensión", required = true)
			@RequestParam final int numberChanges){
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findGraphPatientsDetailsByNumberChanges(numberChanges);
	}
}