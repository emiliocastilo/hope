package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.GroupingFieldTreatmentInfoDTO;
import es.plexus.hopes.hopesback.controller.model.PatientTreatmentDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientTreatmentMapper {

	PatientTreatmentMapper INSTANCE = Mappers.getMapper(PatientTreatmentMapper.class);

	@Mappings({
			@Mapping(source = "type", target = "groupingField")
	})
	GroupingFieldTreatmentInfoDTO entityToTypeTreatmentInfoDto(PatientTreatment entity);

	@Mappings({
			@Mapping(source = "reason", target = "groupingField")
	})
	GroupingFieldTreatmentInfoDTO entityToReasonTreatmentInfoDto(PatientTreatment entity);

	PatientTreatmentDTO entityToPatientTreamentDto(PatientTreatment entity);
}
