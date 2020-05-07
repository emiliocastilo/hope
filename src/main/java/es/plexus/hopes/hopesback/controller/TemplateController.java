package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.TemplateDTO;
import es.plexus.hopes.hopesback.service.TemplateService;
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

@Api(value = "Controlador para recuperar la contraseña del email", tags = "template")
@RestController
@RequestMapping(TemplateController.TEMPLATE_MAPPING)
public class TemplateController {

	static final String TEMPLATE_MAPPING = "/templates";
	private final TemplateService templateService;

	public TemplateController(final TemplateService templateService) {
		this.templateService = templateService;
	}

	@ApiOperation("Recuperar template por contraseña")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TemplateDTO getByKey(@ApiParam(value = "clave template", required = true) @RequestParam final String key) {
		return templateService.findByKey(key);
	}

	@ApiOperation("Subir template")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void uploadTemplate(@RequestBody @Valid final TemplateDTO dto) {
		templateService.uploadTemplate(dto);
	}

}
