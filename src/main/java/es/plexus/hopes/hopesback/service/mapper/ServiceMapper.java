package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.ServiceDTO;
import es.plexus.hopes.hopesback.repository.model.Service;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

	ServiceDTO serviceToServiceDTOConverter(final Service service);

	Service serviceDTOToServiceConverter(final ServiceDTO serviceDTO);
}
