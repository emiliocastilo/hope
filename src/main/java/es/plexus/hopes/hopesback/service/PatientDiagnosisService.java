package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.CieInfoDTO;
import es.plexus.hopes.hopesback.controller.model.IndicationInfoDTO;
import es.plexus.hopes.hopesback.repository.PatientDiagnosisRepository;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.service.mapper.PatientDiagnosisMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientDiagnosisService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final PatientDiagnosisRepository patientDiagnosisRepository;

	/**
	 * Method that it return a list with the number of patients group by indication in the Patient Diagnose Section
	 * @return
	 */
	public List<IndicationInfoDTO> findPatientsByIndication(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.patientsByIndication();
		return patientDiagnosisList.stream().map(PatientDiagnosisMapper.INSTANCE::entityToIndicationInfoDto).collect(Collectors.toList());
	}

	/**
	 * Method that it return a list with a number of patients group by CIE9 in the Patient Diagnose Section
	 * @return
	 */
	public List<CieInfoDTO> findPatientsByCie9(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.patientsByCIE9();
		return patientDiagnosisList.stream().map(PatientDiagnosisMapper.INSTANCE::entityToCieInfoDto).collect(Collectors.toList());
	}

	/**
	 * Method that it return a list with a number of patients group by CIE10 in the Patient Diagnose Section
	 * @return
	 */
	public List<CieInfoDTO> findPatientsByCie10(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.patientsByCIE10();
		return patientDiagnosisList.stream().map(PatientDiagnosisMapper.INSTANCE::entityToCieInfoDto).collect(Collectors.toList());
	}
}
