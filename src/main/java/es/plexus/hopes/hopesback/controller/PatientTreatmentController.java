package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.controller.model.MedicineDosis;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	private static final String CALLING_SERVICE = "Calling service...";

	private final PatientTreatmentService patientTreatmentService;
	
	@ApiOperation("Recuperar los pacientes bajo tratameinto (medicamento)")
	@GetMapping(FIND_PATIENTS_UDER_TREATMENT)
	public Map<String, Long> findPatientsUnderTreatment(@RequestParam(value = "type") String type, @RequestParam(value = "indication", required = false) String indication) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findPatientsUnderTreatment(type, indication);
	}
	
	@ApiOperation("Recuperar los relacion de dosis - paciente")
	@GetMapping(FIND_INFO_PATIENTS_DOSES)
	public Map<String, Long> findInfoPatientsDoses() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findInfoPatientsDoses();
	}

	@ApiOperation("Recuperar los relacion de dosis - paciente y Medicina")
	@GetMapping(FIND_INFO_PATIENTS_DOSES_MEDICINE_DOSIS)
	public List<MedicineDosis> findInfoPatientsDosesMedicine() {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.findInfoPatientsDosesMedicines();
	}


	@ApiOperation("Detalle de pacientes bajo tratamiento")
	@GetMapping(GET_DETAIL_PATIENTS_UDER_TREATMENT)
	public Page<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(@RequestParam(value = "type") String type, @RequestParam(value = "indication", required = false) String indication,
																	   @RequestParam(value="actIngredient", required = false) String medicine,@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.getDetailPatientsUnderTreatment(type, indication, medicine, pageable);
	}
	
	@ApiOperation("Detalle de pacientes bajo tratamiento para exportar")
	@GetMapping(GET_DETAIL_PATIENTS_UDER_TREATMENT_EXPORT)
	public List<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(@RequestParam(value = "type") String type, @RequestParam(value = "indication", required = false) String indication,
																	   @RequestParam(value = "actIngredient", required = false) String medicine) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.getDetailPatientsUnderTreatment(type, indication, medicine);
	}
	
	@ApiOperation("Detalle de pacientes/dosis")
	@GetMapping(GET_DETAIL_PATIENTS_PER_DOSES)
	public Page<GraphPatientDetailDTO> getDetailPatientsPerDoses(
			@RequestParam(value = "regimen")String regimen,
			@PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.getDetailPatientsPerDoses(regimen, pageable);
	}
	
	@ApiOperation("Detalle de pacientes/dosis para exportar")
	@GetMapping(GET_DETAIL_PATIENTS_PER_DOSES_EXPORT)
	public List<GraphPatientDetailDTO> getDetailPatientsPerDoses(@RequestParam(value = "regimen")String regimen) {
		log.debug(CALLING_SERVICE);
		return patientTreatmentService.getDetailPatientsPerDoses(regimen);
	}
}
