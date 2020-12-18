package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.IndicationDTO;
import es.plexus.hopes.hopesback.service.IndicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Controlador de indicaciones", tags = "indication")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(IndicationController.INDICATION_MAPPING)
public class IndicationController {

    static final String INDICATION_MAPPING = "/indications";
    private static final String CALLING_SERVICE = "Calling service...";
    private final IndicationService indicationService;

    @ApiOperation("Recuperar todas las indicaciones de una patologia")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<IndicationDTO> findAll(@RequestHeader(name = "Authorization") final String token) {
        log.debug(CALLING_SERVICE);
        return indicationService.findAll(token);
    }
}
