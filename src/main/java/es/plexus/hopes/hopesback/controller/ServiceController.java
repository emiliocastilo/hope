package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.ServiceDTO;
import es.plexus.hopes.hopesback.service.ServiceService;
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
import java.util.Optional;

@Api(value = "Controlador de servicios", tags = "services")
@RestController
@RequestMapping(ServiceController.SERVICE_MAPPING)
public class ServiceController {

	static final String SERVICE_MAPPING = "/services";

	private final ServiceService serviceService;

	@Autowired
	public ServiceController(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@ApiOperation("Recuperar todos los servicios")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<ServiceDTO> getAllServices() {
		return serviceService.getAllServices();
	}

	@ApiOperation("Buscar un servicio por el identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public ServiceDTO findById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id){
		return serviceService.findById(id);
	}
}
