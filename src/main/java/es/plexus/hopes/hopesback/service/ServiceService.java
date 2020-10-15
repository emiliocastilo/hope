package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.ServiceDTO;
import es.plexus.hopes.hopesback.repository.ServiceRepository;
import es.plexus.hopes.hopesback.service.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceService {

	private final ServiceRepository serviceRepository;
	private final ServiceMapper serviceMapper;

	@Autowired
	public ServiceService(final ServiceRepository serviceRepository, final ServiceMapper serviceMapper) {
		this.serviceRepository = serviceRepository;
		this.serviceMapper = serviceMapper;
	}

	public List<ServiceDTO> getAllServices() {
		return serviceRepository.findAll().stream().map(serviceMapper::serviceToServiceDTOConverter)
				.collect(Collectors.toList());
	}

	public Optional<es.plexus.hopes.hopesback.repository.model.Service> getOneServiceById(final Long id) {
		return serviceRepository.findById(id);
	}

	public ServiceDTO findById(Long id) {
		Optional<es.plexus.hopes.hopesback.repository.model.Service> service = serviceRepository.findById(id);
		ServiceDTO serviceDTO = null;
		if (service.isPresent()){
			serviceDTO =  serviceMapper.serviceToServiceDTOConverter(service.get());
		}
		return serviceDTO;
	}
}
