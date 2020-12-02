package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.FormCardiovascularRiskDTO;
import es.plexus.hopes.hopesback.service.FormulaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Controlador de formulas", tags = "formula")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(FormulaController.FORMULA_MAPPING)
public class FormulaController {

    static final String FORMULA_MAPPING = "/formulas";

    private static final String CALLING_SERVICE = "Calling service...";

    private final FormulaService formulaService;

    @ApiOperation("Calculadora de riesgo cardio vascular")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cardiovascularRiskCalculator")
    public String cardiovascularRiskCalculator(
            @ApiParam(value = "Edad")
            @RequestParam(value = "age") final int age,
            @ApiParam(value = "Genero")
            @RequestParam(value = "gender") final String gender,
            @ApiParam(value = "Es diabetico")
            @RequestParam(value = "diabetic") final boolean diabetic,
            @ApiParam(value = "Es fumador")
            @RequestParam(value = "smoker") final boolean smoker,
            @ApiParam(value = "Presion arterial sistolica")
            @RequestParam(value = "pas") final int pas,
            @ApiParam(value = "Presion arterial diastolica")
            @RequestParam(value = "pad") final int pad,
            @ApiParam(value = "Colesterol total")
            @RequestParam(value = "cholesterolTotal") final int cholesterolTotal,
            @ApiParam(value = "Colesterol HDL")
            @RequestParam(value = "hdl", required = false) final int hdl
    ) {
        log.debug(CALLING_SERVICE);
        FormCardiovascularRiskDTO formCardiovascularRiskDTO = new FormCardiovascularRiskDTO();
        formCardiovascularRiskDTO.setAge(age);
        formCardiovascularRiskDTO.setGender(gender);
        formCardiovascularRiskDTO.setDiabetic(diabetic);
        formCardiovascularRiskDTO.setSmoker(smoker);
        formCardiovascularRiskDTO.setPas(pas);
        formCardiovascularRiskDTO.setPad(pad);
        formCardiovascularRiskDTO.setCholesterolTotal(cholesterolTotal);
        formCardiovascularRiskDTO.setHdl(hdl);
        return formulaService.cardiovascularRiskCalculator(formCardiovascularRiskDTO);
    }
}
