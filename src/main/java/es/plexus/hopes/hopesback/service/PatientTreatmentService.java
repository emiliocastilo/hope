package es.plexus.hopes.hopesback.service;

import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientTreatmentService {

	private static final String CALLING_DB = "Calling DB...";

	private final PatientTreatmentRepository patientTreatmentRepository;

	public Map<String, Long> findPatientsUnderTreatment(String type, String indication) {
		log.debug(CALLING_DB);
		Map<String, Long> result = fillPatientsUnderTreatment(type, indication);
		return result;
	}	
	
	public  Map<String, Long> findPatientTreatmentByTreatment() {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList = fillPatientTreatmentListByTreatmentType();
		return patientTreatmentList.stream()
				.collect(groupingBy(PatientTreatment::getType, Collectors.counting()));
	}

	public Map<String, Long> findPatientTreatmentByCombinedTreatment() {
		log.debug(CALLING_DB);
		Map<Long, String> combinedLabels = new HashMap<>();
		List<PatientTreatment> patientTreatmentList = fillPatientTreamentListByCombinedTreament(combinedLabels);

		return patientTreatmentList.stream()
				.collect(groupingBy(PatientTreatment::getType, Collectors.counting()));
	}

	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatment(String endCause) {
		log.debug(CALLING_DB);
		List<PatientTreatment> removePatientTreatment = new ArrayList<>();
		List<Long> idPatients = new ArrayList<>();
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByEndCauseBiologicTreatment(endCause);

		for(PatientTreatment pt:patientTreatmentList){
			if(idPatients.contains(pt.getPatient().getId())){
				removePatientTreatment.add(pt);
			}else{
				idPatients.add(pt.getPatient().getId());
			}
		}

		patientTreatmentList.removeAll(removePatientTreatment);

		return patientTreatmentList.stream()
				.collect(groupingBy(PatientTreatment::getReason, Collectors.counting()));

	}

	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(String endCause) {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository
						.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(endCause, LocalDateTime.now().plusYears(-5));
		return patientTreatmentList.stream()
				.collect(groupingBy(PatientTreatment::getReason, Collectors.counting()));
	}

	public Map<Long, Integer> findPatientTreatmentByNumberChangesOfBiologicTreatment() {
		log.debug(CALLING_DB);
		Map<Long, Integer> result = fillPatientTreatmentMapByNumberChangesOfBiologicalTreatment();
		return result;
	}

	private List<PatientTreatment> fillPatientTreatmentListByTreatmentType() {
		List<PatientTreatment> patientTreatmentList = patientTreatmentRepository.findPatientTreatmentByTreatment();
		List<PatientTreatment> patientWithoutTreatmentList = patientTreatmentRepository.findPatientTreatmentByWithoutTreatment();
		patientWithoutTreatmentList.forEach(pt -> pt.setType("Sin Tratamiento"));
		patientTreatmentList.addAll(patientWithoutTreatmentList);
		List<PatientTreatment> patientCombinedTreatmentList = patientTreatmentRepository.findPatientTreatmentByCombinedTreatment();
		patientCombinedTreatmentList.forEach(pt -> pt.setType("Tratamiento Combinado"));
		patientTreatmentList.addAll(patientCombinedTreatmentList);
		return patientTreatmentList;
	}

	private List<PatientTreatment> fillPatientTreamentListByCombinedTreament(Map<Long, String> combinedLabels) {
		List<PatientTreatment> patientTreatmentList = patientTreatmentRepository.findPatientTreatmentByCombinedTreatment();
		for(PatientTreatment pt:patientTreatmentList){
			if(combinedLabels.containsKey(pt.getPatient().getId())){
				String lbl = combinedLabels.get(pt.getPatient().getId()) + " + " + pt.getType();
				combinedLabels.replace(pt.getPatient().getId(), lbl);
			}else{
				combinedLabels.put(pt.getPatient().getId(), pt.getType());
			}
		}
		patientTreatmentList.forEach(pt -> pt.setType(combinedLabels.get(pt.getPatient().getId())));
		return patientTreatmentList;
	}

	private Map<Long, Integer> fillPatientTreatmentMapByNumberChangesOfBiologicalTreatment() {
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByNumberChangesOfBiologicTreatment();
		Map<Patient, Long> map = patientTreatmentList.stream()
				.collect(groupingBy(PatientTreatment::getPatient, Collectors.counting()));
		Map<Long, Integer> result = new HashMap<>();
		map.entrySet().stream().forEach(m -> {
			if(result.containsKey(m.getValue())){
				result.replace(m.getValue(), result.get(m.getValue())+1);
			}else{
				result.put(m.getValue(), 1);
			}

		});
		return result;
	}
	
	private Map<String, Long> fillPatientsUnderTreatment(String type, String indication) {
		List<PatientTreatment> patientPatientTreatmentList = patientTreatmentRepository.findPatientsUnderTreatment(type, indication);
		Map<Medicine, Long> map = patientPatientTreatmentList.stream().collect(groupingBy(
				PatientTreatment::getMedicine, 
				Collectors.counting()));
		Map<String, Long> result = new HashMap<>();
		map.entrySet().stream().forEach(m -> {			
			result.put(m.getKey().getActIngredients(), m.getValue());
		});
		return result;
	}

}
