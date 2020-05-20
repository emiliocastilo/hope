package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.PatientDiagnosisRepository;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findAll();
		return patientDiagnosisList.stream().collect(groupingBy(PatientDiagnose::getIndication, Collectors.counting()));
	}

	/**
	 * Method that it return a list with a number of patients group by CIE9 in the Patient Diagnose Section
	 * @return
	 */
	public Map<String, Long>  findPatientsByCie9(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findAll();
		return patientDiagnosisList.stream().collect(groupingBy(PatientDiagnose::getCie9Desc, Collectors.counting()));
	}

	/**
	 * Method that it return a list with a number of patients group by CIE10 in the Patient Diagnose Section
	 * @return
	 */
	public Map<String, Long>  findPatientsByCie10(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findAll();
		return patientDiagnosisList.stream().collect(groupingBy(PatientDiagnose::getCie10Desc, Collectors.counting()));
	}

	/**
	 * Method that it return a list pageable with the patient details by indication in the Patient Diagnose Section
	 * @return
	 */
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByIndication(final String indication, final Pageable pageable){
		log.debug(CALLING_DB);
		return patientDiagnosisRepository
				.findPatientDetailsGraphsByIndication(indication, pageable);
	}

	/**
	 * Method that it return a list with the patient details by indication in the Patient Diagnose Section
	 * @return
	 */
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByIndication(final String indication){
		log.debug(CALLING_DB);
		return patientDiagnosisRepository
				.findPatientDetailsGraphsByIndication(indication);
	}

	/**
	 * Method that it return a list pageable the patient details by CIE9 in the Patient Diagnose Section
	 * @return
	 */
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCie9(final String cie9, final Pageable pageable){
		log.debug(CALLING_DB);
		return patientDiagnosisRepository
				.findPatientDetailsGraphsByCie9(cie9, pageable);
	}

	/**
	 * Method that it return a list the patient details by CIE9 in the Patient Diagnose Section
	 * @return
	 */
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCie9(final String cie9){
		log.debug(CALLING_DB);
		return  patientDiagnosisRepository
				.findPatientDetailsGraphsByCie9(cie9);
	}

	/**
	 * Method that it return a list pageable the patient details by CIE10 in the Patient Diagnose Section
	 * @return
	 */
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCie10(final String cie10, final Pageable pageable){
		log.debug(CALLING_DB);
		return  patientDiagnosisRepository
				.findPatientDetailsGraphsByCie10(cie10, pageable);
	}

	/**
	 * Method that it return a list the patient details by CIE10 in the Patient Diagnose Section
	 * @return
	 */
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCie10(final String cie10){
		log.debug(CALLING_DB);
		return patientDiagnosisRepository
				.findPatientDetailsGraphsByCie10(cie10);
	}
}
