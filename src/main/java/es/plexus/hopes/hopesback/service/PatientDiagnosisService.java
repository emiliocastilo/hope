package es.plexus.hopes.hopesback.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.PatientDataRepository;
import es.plexus.hopes.hopesback.repository.PatientDiagnosisRepository;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientData;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientDiagnosisService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final PatientDiagnosisRepository patientDiagnosisRepository;
	private final PatientDataRepository patientDataRepository;

	/**
	 * Method that it return a list with the number of patients group by indication in the Patient Diagnose Section
	 * @return
	 */
	public Map<String, Map<Boolean,Integer>> findPatientsByIndication(){
		log.debug(CALLING_DB);
		Map<String, Map<Boolean,Integer>> patientsByIndicationMap = new HashMap<>();

		// Lista de diagnósticos de paciente
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findPatientsDiagnosisGroupByIndication();

		// Mapa de pacientes por indicación
		Map<Patient, String> mapPatientsByIndications = patientDiagnosisList.stream()
				.collect(Collectors.toMap(PatientDiagnose::getPatient, pd-> pd.getIndication().getDescription()));

		// Mapa de Pacientes con/sin psoriasis artritica
		buildMapArtritisPatientsByIndication(patientsByIndicationMap, patientDiagnosisList, mapPatientsByIndications);

		return patientsByIndicationMap;
	}


	/**
	 * Method that it return a list with a number of patients group by CIE9 in the Patient Diagnose Section
	 * @return
	 */
	public Map<String, Long>  findPatientsByCie9(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findPatientsDiagnosisGroupByCie9();
		return patientDiagnosisList.stream()
				.collect(groupingBy(pd -> pd.getCie9().getDescription(), Collectors.counting()));
	}

	/**
	 * Method that it return a list with a number of patients group by CIE10 in the Patient Diagnose Section
	 * @return
	 */
	public Map<String, Long>  findPatientsByCie10(){
		log.debug(CALLING_DB);
		List<PatientDiagnose> patientDiagnosisList = patientDiagnosisRepository.findPatientsDiagnosisGroupByCie10();
		return patientDiagnosisList.stream()
				.collect(groupingBy(pd -> pd.getCie10().getDescription(), Collectors.counting()));
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
	
	public PatientDiagnose save(final PatientDiagnose patientDiagnose) {
		log.debug(CALLING_DB);
		return patientDiagnosisRepository.saveAndFlush(patientDiagnose);
	}

	/**
	 * Method that build the map with the patients by indication and it having count the indicator artritis
	 * @param patientsByIndicationMap
	 * @param patientDiagnosisList
	 * @param mapPatientsByIndications
	 */
	private void buildMapArtritisPatientsByIndication(Map<String, Map<Boolean, Integer>> patientsByIndicationMap, List<PatientDiagnose> patientDiagnosisList, Map<Patient, String> mapPatientsByIndications) {
		Map<Patient, Boolean> mapPsoriasisArtriticaPatients = patientDataRepository
				.findPatientsDatasByPatientsIds(
						patientDiagnosisList.stream()
								.mapToLong(pd -> pd.getPatient().getId()).boxed().collect(Collectors.toList())
				).stream().collect(Collectors.toMap(PatientData::getPatient, PatientData::isPsoriatric));

		patientDiagnosisList.forEach(pdg -> {
			if(!mapPsoriasisArtriticaPatients.containsKey(pdg.getPatient())){
				mapPsoriasisArtriticaPatients.put(pdg.getPatient(), false);
			}
		});

		mapPatientsByIndications.forEach((k, v)-> {
			Map<Boolean, Integer> map = new HashMap<>();
			if(!patientsByIndicationMap.containsKey(v)){
				map.put(mapPsoriasisArtriticaPatients.get(k),1);
				patientsByIndicationMap.put(v, map);
			} else if(patientsByIndicationMap.containsKey(v)
					&& !patientsByIndicationMap.get(v).containsKey(mapPsoriasisArtriticaPatients.get(k))){
				map = patientsByIndicationMap.get(v);
				map.put(mapPsoriasisArtriticaPatients.get(k),1);
				patientsByIndicationMap.replace(v, map);
			}else{
				map = patientsByIndicationMap.get(v);
				map.replace(mapPsoriasisArtriticaPatients.get(k),patientsByIndicationMap.get(v).get(mapPsoriasisArtriticaPatients.get(k))+1);
				patientsByIndicationMap.replace(v,map);
			}
		});
	}
	
}
