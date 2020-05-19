package es.plexus.hopes.hopesback.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Api(value = "Controlador de Patients Treatments", tags = "patients-treatments")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/patients-treatments")
public class PatientTreatmentController {
	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientTreatmentService patientTreatmentService;
	
	@ApiOperation("Recuperar los pacientes bajo tratameinto (medicamento)")
	@GetMapping("/find-patients-under-treatment")
	public Map<String, Long> findPatientsUnderTreatment(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "indication", required = false) String indication) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientsUnderTreatment(type, indication);
	}
	
	/*@ApiOperation("Recuperar los relacion de dosis - paciente")
	@GetMapping("/info-patients-doses")
	public List<PatientDosesInfoDTO> infoPatientsDoses() {
		log.debug(CALLING_SERVICE);
		return graphsService.infoPatientsDoses();
	}
	
	/*@ApiOperation("Detalle de pacientes bajo tratamiento")
	@GetMapping("/details-graphs")
	public Page<DetailGraphDTO> detailsGrapths(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "indication", required = false) String indication, @PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return graphsService.detailsGrapths(type, indication, pageable);
	}*/
}
