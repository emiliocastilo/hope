package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientTreatmentService {

	private static final String CALLING_DB = "Calling DB...";
	public static final String COMBINED_TYPE_TREATMENT = "combinado";

	private final PatientTreatmentRepository patientTreatmentRepository;

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
		Map<Patient, Long> patientLongMap = fillPatientTreatmentMapByNumberChangesOfBiologicalTreatment();
		return buildMapPatientsByNumberChangesOfBiologicalTreatment(patientLongMap);
	}

	public Map<String, Long> findPatientsUnderTreatment(String type, String indication) {
		log.debug(CALLING_DB);
		return fillPatientsUnderTreatment(type, indication);
	}
	
	public Map<String, Long> findInfoPatientsDoses() {
		log.debug(CALLING_DB);
		List<PatientTreatment> infoPatientsDosesList = patientTreatmentRepository.findInfoPatientsDoses();
		for (PatientTreatment pt : infoPatientsDosesList) {
			if(pt.getRegimen() == null || pt.getRegimen().isEmpty()) pt.setRegimen("Sin régimen");
		}
		return infoPatientsDosesList
				.stream()
				.collect(groupingBy(PatientTreatment::getRegimen, Collectors.counting()));
	}
	
	public Page<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(final String type, final String  indication, final Pageable pageable) {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.getDetailPatientsUnderTreatment(type, indication, pageable);		
	}
	
	public List<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(final String type, final String  indication) {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.getDetailPatientsUnderTreatment(type, indication);		
	}
	
	public Page<GraphPatientDetailDTO> getDetailPatientsPerDoses(final Pageable pageable) {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.getDetailPatientsPerDoses(pageable);		
	}
	
	public List<GraphPatientDetailDTO> getDetailPatientsPerDoses() {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.getDetailPatientsPerDoses();		
	}

	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByTypeTreatment(String treatmentType, Pageable pageable) {
		log.debug(CALLING_DB);
		List<GraphPatientDetailDTO> combinedTreatmentPatients = findGraphPatientsDetailsByTypeTreatment(treatmentType);
		return new PageImpl(combinedTreatmentPatients, pageable, combinedTreatmentPatients.size());
	}

	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByTypeTreatment(final String treatmentType) {
		List<GraphPatientDetailDTO> combinedTreatmentPatients;
		if(COMBINED_TYPE_TREATMENT.equalsIgnoreCase(treatmentType)){
			combinedTreatmentPatients = patientTreatmentRepository.findPatientGraphDetailsByCombinedTreatment();
			removeRepeatElementOfGraphPatientsDetailList(combinedTreatmentPatients);

		} else{
			combinedTreatmentPatients = patientTreatmentRepository.findPatientDetailsGraphsByTypeTreatment(treatmentType);
		}
		return combinedTreatmentPatients;
	}

	private void removeRepeatElementOfGraphPatientsDetailList(List<GraphPatientDetailDTO> combinedTreatmentPatients) {
		Map<Long, GraphPatientDetailDTO> mapGraphPatientDetail = new HashMap<>();
		combinedTreatmentPatients.forEach(gp ->{
			if(!mapGraphPatientDetail.containsKey(gp.getId())){
				mapGraphPatientDetail.put(gp.getId(), gp);
			}
		});
		combinedTreatmentPatients.clear();
		combinedTreatmentPatients.addAll(mapGraphPatientDetail.entrySet().stream().map(m -> m.getValue()).collect(Collectors.toList()));
	}

	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatment(
			final String endCause, final String reason, final Pageable pageable) {
		return patientTreatmentRepository
				.findGraphPatientsDetailsByEndCauseBiologicTreatment(endCause, reason, pageable);
	}

	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatment(
			final String endCause, final String reason ) {
		return patientTreatmentRepository.findGraphPatientsDetailsByEndCauseBiologicTreatment(endCause, reason);
	}

	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears
			(final String endCause,final String reason, final int years, final Pageable pageable) {
		return patientTreatmentRepository.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears
				(endCause, reason, LocalDateTime.now().plusYears(-years), pageable);
	}

	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears
			(final String endCause, final String reason, final int years) {
		return patientTreatmentRepository.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears
				(endCause, reason, LocalDateTime.now().plusYears(-years));
	}

	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByNumberChanges(
			int numberChanges, final Pageable pageable) {
		return patientTreatmentRepository
				.findGraphPatientsDetailsByPatientsIds(obtainPatientsIds(numberChanges), pageable);
	}

	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByNumberChanges(int numberChanges) {
		return patientTreatmentRepository
				.findGraphPatientsDetailsByPatientsIds(obtainPatientsIds(numberChanges));
	}

	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCombiendTreatment(String combinedTreatment, Pageable pageable) {
		List<GraphPatientDetailDTO> listGraphPatientDetailDTO= findGraphPatientsDetailsByCombiendTreatment(combinedTreatment);
		return new PageImpl(listGraphPatientDetailDTO, pageable, listGraphPatientDetailDTO.size());
	}

	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCombiendTreatment(String combinedTreatment) {
		List<Long> patientsIds = obtainsPatientsIdsByCombinedTreatment(combinedTreatment.replace(" ", " + "));
		List<GraphPatientDetailDTO> listGraphPatientDetailDTO = patientTreatmentRepository.findGraphPatientsDetailsByPatientsIds(patientsIds);
		removeRepeatElementOfGraphPatientsDetailList(listGraphPatientDetailDTO);
		return listGraphPatientDetailDTO;
	}

	private List<Long> obtainsPatientsIdsByCombinedTreatment(String combinedTreatment) {
		Map<Long, String> combinedLabels = new HashMap<>();
		List<PatientTreatment> patientTreatmentList = fillPatientTreamentListByCombinedTreament(combinedLabels);
		return patientTreatmentList.stream()
				.filter(pt -> pt.getType().contains(combinedTreatment))
				.mapToLong(pt -> pt.getPatient().getId())
				.boxed()
				.collect(Collectors.toList());
	}

	private List<Long> obtainPatientsIds(int numberChanges) {
		Map<Patient, Long> patientsMap = fillPatientTreatmentMapByNumberChangesOfBiologicalTreatment();
		return patientsMap.entrySet().stream()
				.filter(map -> map.getValue() == numberChanges)
				.mapToLong(map -> map.getKey().getId())
				.boxed()
				.collect(Collectors.toList());
	}

	private List<PatientTreatment> fillPatientTreatmentListByTreatmentType() {
		List<PatientTreatment> patientTreatmentList = patientTreatmentRepository.findPatientTreatmentByTreatment();
		List<PatientTreatment> patientWithoutTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByWithoutTreatment();
		patientWithoutTreatmentList.forEach(pt -> pt.setType("Sin Tratamiento"));
		patientTreatmentList.addAll(patientWithoutTreatmentList);
		List<PatientTreatment> patientCombinedTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByCombinedTreatment();
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

	private Map<Patient, Long> fillPatientTreatmentMapByNumberChangesOfBiologicalTreatment() {
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByNumberChangesOfBiologicTreatment();
		Map<Patient, Long> patientsMaps = patientTreatmentList.stream()
				.collect(groupingBy(PatientTreatment::getPatient, Collectors.counting()));
		List<PatientTreatment> patientTreatmentWithoutChangesList = patientTreatmentRepository.findPatientTreatmentByNoChangesBiologicTreatment();
		patientTreatmentWithoutChangesList.forEach(pt -> {
			if(!patientsMaps.containsKey(pt.getPatient())) {
				patientsMaps.put(pt.getPatient(),0L);
			}
		});
		return patientsMaps;
	}

	private Map<Long, Integer> buildMapPatientsByNumberChangesOfBiologicalTreatment(Map<Patient, Long> map) {
		Map<Long, Integer> result = new HashMap<>();
		map.entrySet().forEach(m -> {
			if(result.containsKey(m.getValue())){
				result.replace(m.getValue(), result.get(m.getValue())+1);
			}else{
				result.put(m.getValue(), 1);
			}

		});
		return result;
	}

	private Map<String, Long> fillPatientsUnderTreatment(String type, String indication) {
		List<PatientTreatment> patientPatientTreatmentList =
				patientTreatmentRepository.findPatientsUnderTreatment(type, indication);
		Map<Medicine, Long> map = patientPatientTreatmentList.stream().collect(groupingBy(
				PatientTreatment::getMedicine, 
				Collectors.counting()));
		Map<String, Long> result = new HashMap<>();
		map.entrySet().forEach(m -> result.put(m.getKey().getActIngredients(), m.getValue()));
		return result;
	}

}
