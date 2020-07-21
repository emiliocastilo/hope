package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.HospitalRepository;
import es.plexus.hopes.hopesback.repository.PatientDataRepository;
import es.plexus.hopes.hopesback.repository.PatientDiagnosisRepository;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Indication;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientData;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static es.plexus.hopes.hopesback.service.utils.GraphPatientDetailUtils.doPaginationGraphPatientDetailDTO;
import static es.plexus.hopes.hopesback.service.utils.GraphPatientDetailUtils.fillGraphPatientDetailDtoList;
import static java.util.stream.Collectors.groupingBy;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientDiagnosisService {

	private static final String CALLING_DB = "Calling DB...";
	public static final String CIE_9 = "CIE9";


	private final PatientDiagnosisRepository patientDiagnosisRepository;
	private final PatientRepository patientRepository;
	private final PatientDataRepository patientDataRepository;
	private final HospitalRepository hospitalRepository;

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
	 * Method that it return a list with a number of patients group by CIE in the Patient Diagnose Section
	 *
	 * @param hospitalId
	 * @return
	 */
	public Map<String, Long> findPatientsByCie(Long hospitalId) {
		log.debug(CALLING_DB);
		Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);
		Map<String, Long> patientDiagnosisMap = null;
		List<PatientDiagnose> patientDiagnosisList;

		if (hospital.isPresent()) {
			if (CIE_9.equalsIgnoreCase(hospital.get().getCie())) {
				patientDiagnosisList = patientDiagnosisRepository.findPatientsDiagnosisGroupByCie9();
			} else {
				patientDiagnosisList = patientDiagnosisRepository.findPatientsDiagnosisGroupByCie10();
			}
			patientDiagnosisMap = patientDiagnosisList.stream()
					.collect(groupingBy(PatientDiagnose::getCieDescription, Collectors.counting()));
		}

		return patientDiagnosisMap;
	}

	/**
	 * Method that it return a list pageable with the patient details by indication in the Patient Diagnose Section
	 * @return
	 */
	@Transactional
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByIndication(final String indication, Pageable pageable){
		log.debug(CALLING_DB);
		log.debug( "INIT: Query Patients By Indication");
		List<Patient> patients = patientRepository.findPatientsByIndication(indication);
		log.debug( "END: Query Patients By Indication");
		List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);
		Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
		return page;
	}

	/**
	 * Method that it return a list with the patient details by indication in the Patient Diagnose Section
	 * @return
	 */

	@Transactional
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByIndication(final String indication){
		log.debug(CALLING_DB);
		log.debug( "INIT: Query Patients By Indication");
		List<Patient> patients = patientRepository.findPatientsByIndication(indication);
		log.debug( "END: Query Patients By Indication");
		return fillGraphPatientDetailDtoList(patients);
	}




	/**
	 * Method that it return a list pageable the patient details by CIE in the Patient Diagnose Section
	 *
	 * @return
	 */
	@Transactional
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCie(final String codeDescription, final Long hospitalId, final Pageable pageable) {
		log.debug(CALLING_DB);

		List<Patient> patients = null;
		Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);

		if (hospital.isPresent()) {
			if (CIE_9.equalsIgnoreCase(hospital.get().getCie())) {
				log.debug( "INIT: Query Patients By Cie9");
				patients = patientRepository.findPatientDetailsGraphsByCie9(codeDescription);
				log.debug( "END: Query Patients By Cie9");
			} else {
				log.debug( "INIT: Query Patients By Cie10");
				patients = patientRepository.findPatientDetailsGraphsByCie10(codeDescription);
				log.debug( "END: Query Patients By Cie10");
			}
		}

		List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);

		return doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
	}

	/**
	 * Method that it return a list the patient details by CIE in the Patient Diagnose Section
	 *
	 * @return
	 */
	@Transactional
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCie(final String codeDescription, final Long hospitalId) {
		log.debug(CALLING_DB);
		List<Patient> patients = null;
		Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);

		if (hospital.isPresent()) {
			if (CIE_9.equalsIgnoreCase(hospital.get().getCie())) {
				log.debug( "INIT: Query Patients By Cie9");
				patients = patientRepository.findPatientDetailsGraphsByCie9(codeDescription);
				log.debug( "END: Query Patients By Cie9");
			} else {
				log.debug( "INIT: Query Patients By Cie10");
				patients = patientRepository.findPatientDetailsGraphsByCie10(codeDescription);
				log.debug( "END: Query Patients By Cie10");
			}
		}

		return fillGraphPatientDetailDtoList(patients);
	}

	public PatientDiagnose save(final PatientDiagnose patientDiagnose) {
		log.debug(CALLING_DB);
		return patientDiagnosisRepository.saveAndFlush(patientDiagnose);
	}

	public PatientDiagnose findByPatient(Patient patient) {
		log.debug(CALLING_DB);
		return patientDiagnosisRepository.findByPatient(patient);
	}

	public PatientDiagnose findByPatientAndIndication(Patient patient, Indication indication) {
		return patientDiagnosisRepository.findByPatientAndIndication(patient, indication);
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
