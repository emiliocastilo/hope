package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.DispensationDTO;
import es.plexus.hopes.hopesback.controller.model.FormDispensationDTO;
import es.plexus.hopes.hopesback.repository.model.Dispensation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface DispensationMapper {

    DispensationMapper INSTANCE = Mappers.getMapper(DispensationMapper.class);

    DispensationDTO entityToDto(Dispensation entity);

    Dispensation dtoToEntity(DispensationDTO dto);

    default Dispensation formDispensationDtoToEntity(FormDispensationDTO formDispensationDTO){
        Dispensation dispensation = new Dispensation();
        dispensation.setDate(LocalDateTime.now());
        dispensation.setStartPeriod(formDispensationDTO.getStartPeriod());
        dispensation.setEndPeriod(formDispensationDTO.getEndPeriod());
        return dispensation;
    }

}
