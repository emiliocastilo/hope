package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.repository.FormMongoRepository;
import es.plexus.hopes.hopesback.service.FormService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Api(value = "Controlador para recuperar los datos guardados en los formularios", tags = "data")
@RestController
@RequestMapping(DataController.DATA_MAPPING)
public class DataController {

	static final String DATA_MAPPING = "/forms";
	private static final String FIND_DATA_BY_PARAM_NAME = "/datas";
	private static final String FIND_FORM_BY_DATE = "/by-date";

	private final FormService formService;

	public DataController(FormService formService, FormMongoRepository formMongoRepository) {
		this.formService = formService;
	}

	@ApiOperation("Guardar los datos de los formularios")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void saveData(@RequestBody @Valid final FormDTO dto, Authentication authentication) {
		formService.saveData(dto, authentication.getName());
	}

	@ApiOperation("Recuperar los datos de los formularios")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public FormDTO getData(@ApiParam(value = "template", required = true) @RequestParam final String template,
						   @ApiParam(value = "identificador", required = true) @RequestParam final Integer patientId) {
		return formService.findByTemplateAndPatientId(template, patientId);
	}

    @ApiOperation("Actualiza los datos de los formularios")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateData(@RequestBody @Valid FormDTO dto, Authentication authentication) throws ServiceException {
        formService.saveData(dto, authentication.getName());
    }

    @ApiOperation("Borra los datos de los formularios")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteData(@RequestParam String template, @RequestParam Integer patientId) throws ServiceException {
        formService.deleteData(template, patientId);
    }


	@ApiOperation("Recuperar los datos de un formulario por fecha")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(FIND_FORM_BY_DATE)
	public FormDTO getDataByDate(@ApiParam(value = "template", required = true) @RequestParam final String template,
						   @ApiParam(value = "identificador", required = true) @RequestParam final Integer patientId,
						   @ApiParam(value = "Fecha del formulario a recuperar", required = true) @RequestParam final Date date) {
		return formService.findByTemplateAndPatientIdAndDate(template, patientId, date);
	}

	@ApiOperation("Recupera el valor de un campo de un formulario")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(FIND_DATA_BY_PARAM_NAME)
	public List<InputDTO> findDataByParamName(@RequestParam String template, @RequestParam Integer patientId, @RequestParam String name) throws ServiceException {
		return formService.findDataByParamName(template, patientId, name);
	}
}
