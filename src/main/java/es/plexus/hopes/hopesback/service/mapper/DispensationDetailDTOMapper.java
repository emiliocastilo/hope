package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.AdherenceDTO;
import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import org.mapstruct.Mapper;

@Mapper
public interface DispensationDetailDTOMapper {

    AdherenceDTO dispensationDetailDTOToAdherenceDTO(DispensationDetailDTO dispensationDetailDTO);

}
