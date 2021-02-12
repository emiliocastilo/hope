package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.FormCardiovascularRiskDTO;
import es.plexus.hopes.hopesback.service.FormulaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/cardiovascularRiskCalculator")
    public String cardiovascularRiskCalculator(
            @ApiParam(value = "Edad")
            @RequestBody FormCardiovascularRiskDTO formCardiovascularRiskDTO
    ) {

        return formulaService.cardiovascularRiskCalculator(formCardiovascularRiskDTO);
    }
}
