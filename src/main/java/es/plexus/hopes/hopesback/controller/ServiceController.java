package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.ServiceDTO;
import es.plexus.hopes.hopesback.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ServiceController.SERVICE_MAPPING)
public class ServiceController {

	static final String SERVICE_MAPPING = "/service";

	private final ServiceService serviceService;

	@Autowired
	public ServiceController(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@GetMapping
	public List<ServiceDTO> getAllServices() {
		return serviceService.getAllServices();
	}
}
