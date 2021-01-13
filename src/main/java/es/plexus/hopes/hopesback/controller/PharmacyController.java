package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PharmacyDTO;
import es.plexus.hopes.hopesback.service.PharmacyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Controlador de farmacia", tags = "farmacia")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(PharmacyController.PHARMACY_MAPPING)
public class PharmacyController {

    static final String PHARMACY_MAPPING = "/pharmacy";

    private static final String CALLING_SERVICE = "Calling service...";
    private final PharmacyService pharmacyService;

    @ApiOperation("Recupera todos los registros de farmacia para todos los pacientes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<PharmacyDTO> findAll(
            @PageableDefault(size = 5) final Pageable pageable) {
        log.debug(CALLING_SERVICE);
        return pharmacyService.findAll(pageable);
    }
}
