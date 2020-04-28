package es.plexus.hopes.hopesback.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.repository.model.DispensationDetail;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.service.spi.ServiceException;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public interface DispensationDetailMapper {

    DispensationDetailMapper INSTANCE = Mappers.getMapper(DispensationDetailMapper.class);
    String PATTERN_DATE_DD_MM_YYYY = "dd/MM/yyyy";

    DispensationDetailDTO entityToDto(DispensationDetail entity);

    DispensationDetail dtoToEntity(DispensationDetailDTO dto);

    default DispensationDetail csvRecordToEntity(CSVRecord csvRecord){
        DispensationDetail dispensationDetail = new DispensationDetail();
        dispensationDetail.setDate(LocalDate.parse(csvRecord.get(0), DateTimeFormatter.ofPattern(PATTERN_DATE_DD_MM_YYYY)).atStartOfDay());
        dispensationDetail.setNhc(csvRecord.get(1));
        dispensationDetail.setCode(csvRecord.get(2));
        dispensationDetail.setNationalCode(Integer.parseInt(csvRecord.get(3)));
        dispensationDetail.setDescription(csvRecord.get(4));
        dispensationDetail.setQuantity(csvRecord.get(5));
        dispensationDetail.setAmount(BigDecimal.valueOf(Double.valueOf(csvRecord.get(6))).setScale(2));
        dispensationDetail.setDaysDispensation(Integer.parseInt(csvRecord.get(7)));
        return dispensationDetail;
    }

    default DispensationDetailDTO jsonToDTO(String json) {
        DispensationDetailDTO dispensationDetailDTO;

        try {
            dispensationDetailDTO = new ObjectMapper().readValue(json, DispensationDetailDTO.class);
        } catch (JsonMappingException e) {
            throw new ServiceException("Fields not belonging to the object are being sent " + e.getMessage());
        } catch (JsonProcessingException e) {
            throw new ServiceException("Filter processing error occurred " + e.getMessage());
        }

        return dispensationDetailDTO;
    }
}
