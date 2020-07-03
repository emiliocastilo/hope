package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.CieDTO;
import es.plexus.hopes.hopesback.service.CieService;
import io.swagger.annotations.Api;
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

@Api(value = "Controlador de diagnósticos CIE", tags = "cie")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(CieController.CIE_MAPPING)
public class CieController {

    static final String CIE_MAPPING = "/cies";

    private static final String CALLING_SERVICE = "Calling service...";

    private final CieService cieService;

    @ApiOperation("Recuperar diagnósticos CIE")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<CieDTO> findAll(
            @ApiParam(value = "Identificador del hospital de la sesión") @RequestParam(value = "hospitalId") final Long hospitalId,
            @PageableDefault(size = 5) final Pageable pageable) {
        log.debug(CALLING_SERVICE);
        return cieService.findAll(hospitalId, pageable);
    }

    @ApiOperation("Buscar diagnósticos CIE por código o por descripción")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/searches")
    public Page<CieDTO> findCieBySearch(
            @ApiParam(value = "Identificador del hospital de la sesión") @RequestParam(value = "hospitalId") final Long hospitalId,
            @ApiParam(value = "buscador") @RequestParam(value = "search", required = false, defaultValue = "") final String search,
            @PageableDefault(size = 5) final Pageable pageable) {
        log.debug(CALLING_SERVICE);
        return cieService.findCieByCodeOrSearch(hospitalId, search, pageable);
    }

}
