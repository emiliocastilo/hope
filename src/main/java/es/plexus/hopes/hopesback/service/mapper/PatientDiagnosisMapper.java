package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.CieInfoDTO;
import es.plexus.hopes.hopesback.controller.model.IndicationInfoDTO;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientDiagnosisMapper {

    PatientDiagnosisMapper INSTANCE = Mappers.getMapper(PatientDiagnosisMapper.class);

	IndicationInfoDTO entityToIndicationInfoDto(PatientDiagnose patientDiagnose);
	CieInfoDTO entityToCieInfoDto(PatientDiagnose patientDiagnose);
}
