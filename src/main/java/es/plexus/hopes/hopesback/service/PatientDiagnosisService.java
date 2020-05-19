package es.plexus.hopes.hopesback.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.repository.PatientDiagnosisRepository;
import es.plexus.hopes.hopesback.repository.model.Indication;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

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
	public Map<String, Long> findPatientsByIndication(){
		log.debug(CALLING_DB);
		Map<String, Long> result = fillPatientsByIndication();
		return result;
	}

	/**
	 * Method that it return a list with a number of patients group by CIE9 in the Patient Diagnose Section
	 * @return
	 */
	public Map<String, Long>  findPatientsByCie9(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findAll();
		return patientDiagnosisList.stream().collect(groupingBy(PatientDiagnose::getCie9Code, Collectors.counting()));
	}

	/**
	 * Method that it return a list with a number of patients group by CIE10 in the Patient Diagnose Section
	 * @return
	 */
	public Map<String, Long>  findPatientsByCie10(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findAll();
		return patientDiagnosisList.stream().collect(groupingBy(PatientDiagnose::getCie10Code, Collectors.counting()));
	}
	
	private Map<String, Long> fillPatientsByIndication() {
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findAll();
		Map<Indication, Long> map = patientDiagnosisList.stream().collect(groupingBy(
				PatientDiagnose::getIndication, 
				Collectors.counting()));
		Map<String, Long> result = new HashMap<>();
		map.entrySet().stream().forEach(m -> {			
			result.put(m.getKey().getDescripcion(), m.getValue());
		});
		return result;
	}
}
