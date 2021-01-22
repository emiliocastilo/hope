package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.service.PatientsClinicalDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "Controlador de Datos clínicos del paciente", tags = "patientClinicalData")
@Log4j2
@RestController
@RequestMapping(PatientClinicalDataController.PATIENT_CLINICAL_DATA)
@RequiredArgsConstructor
public class PatientClinicalDataController {

    public static final String PATIENT_CLINICAL_DATA = "/patient-clinical-data";
    private final PatientsClinicalDataService patientsClinicalDataService;

    @ApiOperation("Recuperar datos clínicos de un paciente")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/by-type")
    public Map<String, Long> getPatientClinicalDataByType(
            @ApiParam(value = "Tipo de dato que se quiere obtener", example = "CVP", required = true)
            @RequestParam(value = "type") String type) {
        return patientsClinicalDataService.getPatientClinicalDataByType(type);
    }


    @ApiOperation("Recuperar indicación de un dato clínico del paciente")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/by-type-indication")
    public Page<GraphPatientDetailDTO> getPatientClinicalDataByTypeAndIndication(
            @ApiParam(value = "Tipo de dato que se quiere obtener", example = "CVP", required = true)
            @RequestParam(value = "type") String type,
            @ApiParam(value = "Tipo de dato que se quiere obtener", example = "CVP", required = true)
            @RequestParam(value = "indication") String indication,
            @PageableDefault(size = 5) Pageable pageable) {
        return patientsClinicalDataService.getPatientClinicalDataByTypeAndIndication(type, indication,pageable);
    }
}