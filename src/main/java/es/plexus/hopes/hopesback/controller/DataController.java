package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Controlador para recuperar los datos guardados en los formularios", tags = "data")
@RestController
@RequestMapping(DataController.DATA_MAPPING)
public class DataController {

	static final String DATA_MAPPING = "/data";

	private final FormService formService;

	public DataController(FormService formService) {
		this.formService = formService;
	}

	@ApiOperation("Guardar los datos de los formularios")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void saveData(@RequestBody @Valid final FormDTO dto) {
		formService.saveData(dto);
	}

	@ApiOperation("Recuperar los datos de los formularios")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public FormDTO getData(@ApiParam(value = "template", required = true) @RequestParam final String template,
						   @ApiParam(value = "identificador", required = true) @RequestParam final Integer patientId) {
		return formService.findByTemplateAndPatientId(template, patientId);
	}

}
