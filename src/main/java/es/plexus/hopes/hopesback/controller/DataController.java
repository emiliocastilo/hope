package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.GraphHistorifyDinamycFormDTO;
import es.plexus.hopes.hopesback.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Api(value = "Controlador para recuperar los datos guardados en los formularios", tags = "data")
@RestController
@RequestMapping(DataController.DATA_MAPPING)
public class DataController {

	static final String DATA_MAPPING = "/forms";
	private static final String FIND_DATA_BY_PARAM_NAME = "/datas";
	private static final String GRAPH_BY_TEMPLATE = "/graphs";
	private static final String FIND_FORM_BY_DATE = "/by-date";
	private static final String FIND_FORMS = "/find-all";

	private final FormService formService;

	public DataController(FormService formService) {
		this.formService = formService;
	}

	@ApiOperation("Guardar los datos de los formularios")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void saveData(@RequestBody @Valid final FormDTO dto, Authentication authentication,
						 @RequestHeader(name = "Authorization") final String token) {
		formService.saveData(dto, authentication.getName(), token);
	}

	@ApiOperation("Recuperar los datos de los formularios")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public FormDTO getData(@ApiParam(value = "template", required = true) @RequestParam final String template,
						   @ApiParam(value = "identificador", required = true) @RequestParam final Integer patientId) {
		return formService.findFormByTemplateAndPatientId(template, patientId);
	}

    @ApiOperation("Actualiza los datos de los formularios")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateData(@RequestBody @Valid FormDTO dto, Authentication authentication,
						   @RequestHeader(name = "Authorization") final String token) {
        formService.saveData(dto, authentication.getName(), token);
    }

    @ApiOperation("Borra los datos de los formularios")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteData(@RequestParam String template, @RequestParam Integer patientId){
        formService.deleteData(template, patientId);
    }


	@ApiOperation("Recuperar los datos de un formulario por fecha")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(FIND_FORM_BY_DATE)
	public FormDTO getFormByDate(@ApiParam(value = "template", required = true) @RequestParam final String template,
									 @ApiParam(value = "identificador", required = true) @RequestParam final Integer patientId,
									 @ApiParam(value = "Fecha del formulario a recuperar", required = true) @RequestParam final LocalDateTime date) {
		return formService.findByTemplateAndPatientIdAndDate(template, patientId, date);
	}

	@ApiOperation("Recuperar los datos de un formulario por fecha")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(FIND_FORMS)
	public List<FormDTO> getFormsByTemplateAndPatientId(@ApiParam(value = "template", required = true) @RequestParam final String template,
								 @ApiParam(value = "identificador", required = true) @RequestParam final Integer patientId) {
		return formService.findByTemplateAndPatientId(template, patientId);
	}

	@ApiOperation("Recupera el valor de un campo de un formulario")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(FIND_DATA_BY_PARAM_NAME)
	public Object findDataByParamName(
			@ApiParam(value = "Key de la Plantilla del formulario dinámico", required = true)
			@RequestParam String template,
			@ApiParam(value = "Identificador del paciente", required = true)
			@RequestParam Integer patientId,
			@ApiParam(value = "Nombre del parámetro del que buscar su valor de la plantilla indicada", required = true)
			@RequestParam String name){
		return formService.findDataByParamName(template, patientId, name);
	}

	@ApiOperation("Recupera los datos para pintar un gráfico de un formulario dinámico historificable")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(GRAPH_BY_TEMPLATE)
	public List<GraphHistorifyDinamycFormDTO> graphsByTemplateHistorify(
			@ApiParam(value = "Key de la Plantilla del formulario dinámico", required = true)
			@RequestParam String template,
			@ApiParam(value = "Identificador del paciente", required = true)
			@RequestParam Integer patientId) {
		return formService.graphHistorifyDinamycFormByTemplateAndPatientId(template, patientId);
	}

}
