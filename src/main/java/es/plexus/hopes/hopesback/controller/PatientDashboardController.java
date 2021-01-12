package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphHealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDashboardDetailDTO;
import es.plexus.hopes.hopesback.service.PatientDashboardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "Controlador de Healthoutcome", tags = "health-outcomes")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/patients-dashboards")
public class PatientDashboardController {
	private static final String FIND_EVOLUTION_CLINICAL_INDICES_BY_INDEX_AND_PATIENT = "/evolution-indices-clinical";
	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientDashboardService patientDashboardService;

	@ApiOperation("Obtiene el cuadro de mandos del paciente")
	@GetMapping("/{id}")
	public PatientDashboardDetailDTO findDashboardPatientByPatientId(
			@ApiParam(value = "Id del paciente", example = "1L", required = true) @PathVariable Long id,
			@RequestHeader(name = "Authorization") final String token) {
		log.debug(CALLING_SERVICE);
		return patientDashboardService.findDashboardPatientByPatientId(id, token);
	}

	@ApiOperation("Listado de valores de salud por tipo índice por paciente")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(FIND_EVOLUTION_CLINICAL_INDICES_BY_INDEX_AND_PATIENT)
	public Map<String, List<GraphHealthOutcomeDTO>> findEvolutionClinicalIndicesResultsByIndexTypeAndPatient(
			@ApiParam(value = "Índice clínico", example = "PASI", required = true)
			@RequestParam(value = "indicesTypes") String indicesTypes,
			@ApiParam(value = "Id del paciente", example = "2", required = true)
			@RequestParam(value = "patId") Long patId) {
		log.debug(CALLING_SERVICE);
		return patientDashboardService.findEvolutionClinicalIndicesByIndexTypeAndPatient(indicesTypes, patId);
	}
}
