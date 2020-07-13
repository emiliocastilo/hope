package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.service.HospitalService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Controlador de hospitales", tags = "hospital")
@RestController
@RequestMapping(HospitalController.HOSPITAL_MAPPING)
public class HospitalController {

	static final String HOSPITAL_MAPPING = "/hospitals";

	private final HospitalService hospitalService;

	@Autowired
	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	@ApiOperation("Recuperar todos los hospitales")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<HospitalDTO> getAllHospitals() {
		return hospitalService.getAllHospitals();
	}

	@ApiOperation("Buscar un hospital por el identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public HospitalDTO findById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) throws ServiceException  {
		return hospitalService.findById(id);
	}
}


