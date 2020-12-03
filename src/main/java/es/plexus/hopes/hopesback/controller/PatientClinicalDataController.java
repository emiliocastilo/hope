package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.service.PatientDiagnosisService;
import es.plexus.hopes.hopesback.service.PatientsClinicalDataService;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping(PatientClinicalDataController.PATIENT_CLINICAL_DATA)
public class PatientClinicalDataController {

    static final String PATIENT_CLINICAL_DATA = "/patient-clinical-data";
    private final PatientsClinicalDataService patientsClinicalDataService;

    public PatientClinicalDataController(PatientsClinicalDataService patientsClinicalDataService, PatientDiagnosisService patientDiagnosisService) {
        this.patientsClinicalDataService = patientsClinicalDataService;

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/by-name")
    public Map<String, Long> getData(
            @ApiParam(value = "Tipo de dato que se quiere obtener", example = "CVP", required = true)
            @RequestParam(value = "type") String type) {
        return patientsClinicalDataService.getPatientsCVP();
    }
}