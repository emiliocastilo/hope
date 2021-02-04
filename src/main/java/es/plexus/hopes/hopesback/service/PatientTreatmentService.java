package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.controller.model.MedicineDosis;
import es.plexus.hopes.hopesback.controller.model.TreatmentDTO;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.service.mapper.PatientTreatmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static es.plexus.hopes.hopesback.service.Constants.TYPE_TREATMENT_BIOLOGICAL;
import static es.plexus.hopes.hopesback.service.Constants.TYPE_TREATMENT_CHEMICAL;
import static es.plexus.hopes.hopesback.service.Constants.TYPE_TREATMENT_FAME;
import static es.plexus.hopes.hopesback.service.utils.GraphPatientDetailUtils.doPaginationGraphPatientDetailDTO;
import static es.plexus.hopes.hopesback.service.utils.GraphPatientDetailUtils.fillGraphPatientDetailDtoList;
import static java.util.stream.Collectors.groupingBy;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class PatientTreatmentService {

	private static final String CALLING_DB = "Calling DB...";
	public static final String COMBINED_TYPE_TREATMENT = "Tratamiento Combinado";
	public static final String NOT_TREATMENT = "Sin Tratamiento";
	public static final String TREATMENT_TYPE_TOPICO_FOTOTERAPIA_QUIMICO = "TOPICO + FOTOTERAPIA + QUIMICO";
	public static final String TREATMENT_TYPE_BIOLOGICO_QUIMICO = "BIOLOGICO + QUIMICO";
	public static final String TREATMENT_TYPE_BIOLOGICO_FOTOTERAPIA = "BIOLOGICO + FOTOTERAPIA";
	public static final String TREATMENT_TYPE_QUIMICO_FOTOTERAPIA = "QUIMICO + FOTOTERAPIA";
	public static final String TREATMENT_TYPE_TOPICO_QUIMICO = "TOPICO + QUIMICO";
	public static final String TREATMENT_TYPE_TOPICO_FOTOTERAPIA = "TOPICO + FOTOTERAPIA";
	public static final String TREATMENT_TYPE_TOPICO = "TOPICO";
	public static final String TREATMENT_TYPE_QUIMICO = "QUIMICO";
	public static final String TREATMENT_TYPE_FOTOTERAPIA = "FOTOTERAPIA";
	public static final String TREATMENT_TYPE_BIOLOGICO = "BIOLOGICO";
	public static final String NO_REGIMEN = "Sin régimen";

	private final PatientTreatmentRepository patientTreatmentRepository;
	private final PatientRepository patientRepository;

	public  Map<String, Long> findPatientTreatmentByTreatmentAndPathology(Pathology pathology) {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList = fillPatientTreatmentListByTreatmentType();
		Map<Patient,List<PatientTreatment>> pacientes = new HashMap<>();
		Map<PatientDiagnose,List<PatientTreatment>> pacientesPT;
		pacientesPT = patientTreatmentList.stream().filter(patientTreatment -> patientTreatment.getPatientDiagnose().getPatient().getPathologies().contains(pathology)).collect(groupingBy(PatientTreatment::getPatientDiagnose));
		pacientesPT.forEach((patientDiagnose, patientTreatments) -> pacientes.put(patientDiagnose.getPatient(), patientTreatments));
		List<String> tratamientos = new ArrayList<>();

		pacientes.forEach((patient, patientTreatments) -> tratamientos.add(patientTreatments.get(0).getType()));
		return tratamientos.stream().collect(Collectors.groupingBy(String::toUpperCase,Collectors.counting()));
	}

	public Map<String, Long> findPatientTreatmentByCombinedTreatmentAndPathology(Pathology pathology) {
		log.debug(CALLING_DB);
		List<String> patientTreatmentList = fillPatientTreamentListByCombinedTreament(pathology);

		return patientTreatmentList.stream()
				.collect(groupingBy(s -> s, Collectors.counting()));
	}

	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatmentAndPathology(String endCause, Pathology pathology) {
		log.debug(CALLING_DB);
		List<PatientTreatment> removePatientTreatment = new ArrayList<>();
		List<Long> idPatients = new ArrayList<>();
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByEndCauseBiologicTreatment(endCause)
						.stream().filter(patientTreatment -> patientTreatment.getPatientDiagnose().getPatient().getPathologies().contains(pathology)).collect(Collectors.toList());

		for(PatientTreatment pt:patientTreatmentList){
			if(idPatients.contains(pt.getPatientDiagnose().getPatient().getId())){
				removePatientTreatment.add(pt);
			}else{
				idPatients.add(pt.getPatientDiagnose().getPatient().getId());
			}
		}

		patientTreatmentList.removeAll(removePatientTreatment);

		return patientTreatmentList.stream()
				.collect(groupingBy(PatientTreatment::getReason, Collectors.counting()));

	}

	public Map<String, Long> findPatientTreatmentByEndCauseBiologicTreatmentAndPathologyInLast5Years(String endCause, Pathology pathology) {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository
						.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(endCause, LocalDateTime.now().plusYears(-5))
						.stream().filter(patientTreatment -> patientTreatment.getPatientDiagnose().getPatient().getPathologies().contains(pathology)).collect(Collectors.toList());
		return patientTreatmentList.stream()
				.collect(groupingBy(PatientTreatment::getReason, Collectors.counting()));
	}

	@Transactional
	public Map<Long, Integer> findPatientTreatmentByNumberChangesOfBiologicTreatment(Pathology pathology) {
		log.debug(CALLING_DB);
		Map<Patient, Long> patientLongMap = fillPatientTreatmentMapByNumberChangesOfBiologicalTreatment(pathology);
		return buildMapPatientsByNumberChangesOfBiologicalTreatment(patientLongMap);
	}

	@Transactional
	public Map<String, Long> findPatientsUnderTreatment(String type, String indication, Pathology pathology) {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientPatientTreatmentList =StringUtils.isEmpty(indication)?
				patientTreatmentRepository.findPatientsUnderTreatment(type):patientTreatmentRepository.findPatientsUnderTreatment(type, indication);
		return patientPatientTreatmentList.stream().filter(patientTreatment -> patientTreatment.getPatientDiagnose().getPatient().getPathologies().contains(pathology)).collect(groupingBy(functionDescriptionMedicineByTreatment(),
				Collectors.counting()));
	}

	public Map<String, Long> findInfoPatientsDoses() {
		log.debug(CALLING_DB);
		List<PatientTreatment> infoPatientsDosesList = patientTreatmentRepository.findInfoPatientsDoses();
		for (PatientTreatment pt : infoPatientsDosesList) {
			if(pt.getRegimen() == null || pt.getRegimen().isEmpty()) pt.setRegimen(NO_REGIMEN);
		}
		return infoPatientsDosesList
				.stream()
				.collect(groupingBy(PatientTreatment::getRegimen, Collectors.counting()));
	}

	public List<MedicineDosis> findInfoPatientsDosesMedicines(Pathology pathology) {
		log.debug(CALLING_DB);
		List<PatientTreatment> infoPatientsDosesList = patientTreatmentRepository.findInfoPatientsDosesWithMedicines().stream().filter(patientTreatment -> patientTreatment.getPatientDiagnose().getPatient().getPathologies().contains(pathology)).collect(Collectors.toList());
		for (PatientTreatment pt : infoPatientsDosesList) {
			if (pt.getRegimen() == null || pt.getRegimen().isEmpty()) pt.setRegimen(NO_REGIMEN);
		}
		List<MedicineDosis> result = new ArrayList<>();

		Map<String, Map<String, Long>> list = infoPatientsDosesList
				.stream()
				.collect(Collectors.groupingBy(patientTreatment -> { return patientTreatment.getMedicine().getActIngredients();}, Collectors.groupingBy(PatientTreatment::getRegimen, Collectors.counting())));
		list.forEach((actIngredient, stringLongMap) -> {
					List<Map<String, String>> regimes = new ArrayList<>();
					stringLongMap.forEach((name, count) -> {
						Map<String, String> values = new HashMap<>();
						values.put("name", name);
						values.put("value", String.valueOf(count));
						regimes.add(values);
					});
					MedicineDosis medicineDosis = new MedicineDosis();
					medicineDosis.setActIngredient(actIngredient);
					medicineDosis.setRegimes(regimes);
					result.add(medicineDosis);
				}
		);
		return result;
	}

	@Transactional
	public Page<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(final String type, final String indication, final String actIngredient, final Pageable pageable, Pathology pathology) {
		log.debug(CALLING_DB);
		List<Patient> patients = patientRepository.getDetailPatientsUnderTreatment(type, indication, actIngredient).stream().filter(patient -> patient.getPathologies().contains(pathology)).collect(Collectors.toList());
		List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);
		Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
		return page;
	}

	@Transactional
	public List<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(final String type, final String indication, String actIngredient, Pathology pathology) {
		log.debug(CALLING_DB);
		List<Patient> patients = patientRepository.getDetailPatientsUnderTreatment(type, indication, actIngredient);
		return fillGraphPatientDetailDtoList(patients);
	}

	@Transactional
	public Page<GraphPatientDetailDTO> getDetailPatientsPerDoses(String regimen, final Pageable pageable, Pathology pathology) {
		log.debug(CALLING_DB);
		List<Patient> patients = patientRepository.getDetailPatientsPerDoses(regimen).stream().filter(patient -> patient.getPathologies().contains(pathology)).collect(Collectors.toList());
		List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);
		Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
		return page;
	}

	@Transactional
	public List<GraphPatientDetailDTO> getDetailPatientsPerDoses(String regimen) {
		log.debug(CALLING_DB);
		List<Patient> patients = patientRepository.getDetailPatientsPerDoses(regimen);
		return fillGraphPatientDetailDtoList(patients);
	}
	@Transactional
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByTypeTreatment(String treatmentType, Pageable pageable) {
		log.debug(CALLING_DB);
		List<GraphPatientDetailDTO> graphPatientDetailList = findGraphPatientsDetailsByTypeTreatment(treatmentType);
		Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
		return page;
	}

	@Transactional
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByTypeTreatment(final String treatmentType) {
		List<Patient> patients;

		if(NOT_TREATMENT.equalsIgnoreCase(treatmentType)){
			log.debug( "INIT: Query Patients By not Treatment");
			patients = patientRepository.findPatientGraphDetailsByNoTreatment();
			log.debug( "END: Query Patients By not Treatment");

		}else if(COMBINED_TYPE_TREATMENT.equalsIgnoreCase(treatmentType)){
			log.debug( "INIT: Query Patients By Treatment When is Combined Treatment");
			patients = patientRepository.findPatientGraphDetailsByCombinedTreatment();
			log.debug( "END: Query Patients By Treatment When is Combined Treatment");

		} else{
			log.debug( "INIT: Query Patients By Treatment When isn't combined treatment");
			patients = patientRepository.findPatientDetailsGraphsByTypeTreatment(treatmentType);
			log.debug( "END: Query Patients By Treatment When isn't combined treatment");
		}
		return fillGraphPatientDetailDtoList(patients);
	}

	@Transactional
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatment(
			final String endCause, final String reason, final Pageable pageable) {
		log.debug( "INIT: Query Patients By End Cause Biologic");
		List<Patient> patients = patientRepository.findGraphPatientsDetailsByEndCauseBiologicTreatment(endCause, reason);
		log.debug( "END: Query Patients By End Cause Biologic");
		List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);
		Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
		return page;
	}

	@Transactional
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatment(
			final String endCause, final String reason ) {
		log.debug( "INIT: Query Patients By End Cause Biologic");
		List<Patient> patients = patientRepository.findGraphPatientsDetailsByEndCauseBiologicTreatment(endCause, reason);
		log.debug( "END: Query Patients By End Cause Biologic");
		return fillGraphPatientDetailDtoList(patients);
	}

	@Transactional
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears
			(final String endCause,final String reason, final int years, final Pageable pageable) {
		log.debug( "INIT: Query Patients By End Cause Biologic In last years");
		List<Patient> patients = patientRepository.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears
				(endCause, reason, LocalDateTime.now().plusYears(-years));
		log.debug( "END: Query Patients By End Cause Biologic In last years");
		List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);
		Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
		return page;
	}

	@Transactional
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears
			(final String endCause, final String reason, final int years) {
		log.debug( "INIT: Query Patients By End Cause Biologic In last years");
		List<Patient> patients = patientRepository.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears
				(endCause, reason, LocalDateTime.now().plusYears(-years));
		log.debug( "END: Query Patients By End Cause Biologic In last years");
		return fillGraphPatientDetailDtoList(patients);
	}

	@Transactional
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByNumberChanges(
			int numberChanges, final Pageable pageable, Pathology pathology) {
		log.debug( "INIT: Query Patients By Number Changes");
		List<Patient> patients = patientRepository.findGraphPatientsDetailsByPatientsIds(obtainPatientsIds(numberChanges,pathology));
		log.debug( "END: Query Patients By Number Changes");
		List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);
		Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
		return page;
	}

	@Transactional
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByNumberChanges(int numberChanges, Pathology pathology) {
		log.debug( "INIT: Query Patients By Number Changes");
		List<Patient> patients = patientRepository.findGraphPatientsDetailsByPatientsIds(obtainPatientsIds(numberChanges, pathology));
		log.debug( "END: Query Patients By Number Changes");
		return fillGraphPatientDetailDtoList(patients);
	}

	@Transactional
	public Page<GraphPatientDetailDTO> findGraphPatientsDetailsByCombiendTreatment(String combinedTreatment, Pageable pageable) {
		List<String> typesTreatmentsList = obtainTreatmentTypes(combinedTreatment);
		List<Patient> patients = patientRepository
				.findGraphPatientsDetailsByCombinedTreatments(typesTreatmentsList, Long.valueOf(typesTreatmentsList.size()));
		List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);
		Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
		return page;
	}

	@Transactional
	public List<GraphPatientDetailDTO> findGraphPatientsDetailsByCombiendTreatment(String combinedTreatment) {
		List<String> typesTreatmentsList = obtainTreatmentTypes(combinedTreatment);
		List<Patient> patients = patientRepository
				.findGraphPatientsDetailsByCombinedTreatments(typesTreatmentsList, Long.valueOf(typesTreatmentsList.size()));
		return fillGraphPatientDetailDtoList(patients);
	}

	public Map<String,List<TreatmentDTO>> findTreatmentsByPatientId(Long patId) {
		Map<String, List<TreatmentDTO>> map = new HashMap<>();
		List<PatientTreatment> patientBiologicalTreatmentList = patientTreatmentRepository
																	.findBiologicalTreatmentsByPatientId(patId);
		map.put(TYPE_TREATMENT_BIOLOGICAL,patientBiologicalTreatmentList.stream()
				.map(Mappers.getMapper(PatientTreatmentMapper.class)::entityToTreatmentDTO)
				.collect(Collectors.toList()));
		List<PatientTreatment> patientFameTreatmentList = patientTreatmentRepository
															.findFameTreatmentsByPatientId(patId);
		map.put(TYPE_TREATMENT_FAME,patientFameTreatmentList.stream()
				.map(Mappers.getMapper(PatientTreatmentMapper.class)::entityToTreatmentDTO)
				.collect(Collectors.toList()));
		return map;
	}

	public Map<String,List<TreatmentDTO>> findVIHTreatmentsByPatientId(Long patId) {
		Map<String, List<TreatmentDTO>> map = new HashMap<>();
		List<PatientTreatment> patientFameTreatmentList = patientTreatmentRepository
				.findVIHTreatmentsByPatientIdAndType(patId, TYPE_TREATMENT_CHEMICAL);
		map.put(TYPE_TREATMENT_CHEMICAL,patientFameTreatmentList.stream()
				.map(Mappers.getMapper(PatientTreatmentMapper.class)::entityToTreatmentDTO)
				.collect(Collectors.toList()));
		return map;
	}

	private List<String> obtainTreatmentTypes(String combinedTreatment) {
		List<String> typesTreatmentsList  = new ArrayList<>();
		String[] typesTreatments = combinedTreatment.contains("+")?
				combinedTreatment.replace(" ","").split("\\+"):
				combinedTreatment.split(" ");
		Arrays.asList(typesTreatments).forEach(t -> {
			if(!StringUtils.isEmpty(t)){
				typesTreatmentsList.add(t.toLowerCase());
			}
		});
		return typesTreatmentsList;
	}

	private List<Long> obtainPatientsIds(int numberChanges, Pathology pathology) {
		Map<Patient, Long> patientsMap = fillPatientTreatmentMapByNumberChangesOfBiologicalTreatment(pathology);
		return patientsMap.entrySet().stream()
				.distinct()
				.filter(map -> map.getValue() == numberChanges)
				.mapToLong(map -> map.getKey().getId())
				.boxed()
				.collect(Collectors.toList());
	}

	private List<PatientTreatment> fillPatientTreatmentListByTreatmentType() {
		List<PatientTreatment> patientTreatmentList = patientTreatmentRepository.findPatientTreatmentByTreatment();
		List<PatientTreatment> patientWithoutTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByWithoutTreatment();
		//patientWithoutTreatmentList.forEach(pt -> pt.setType(NOT_TREATMENT));
		patientTreatmentList.addAll(patientWithoutTreatmentList);
		List<PatientTreatment> patientCombinedTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByCombinedTreatment();
		//patientCombinedTreatmentList.forEach(pt -> pt.setType("Tratamiento Combinado"));
		patientTreatmentList.addAll(patientCombinedTreatmentList);
		return patientTreatmentList;
	}

	// Se lanza la query que obtiene los pacientes con mas de 2 tratamientos activos
	private List<String> fillPatientTreamentListByCombinedTreament(Pathology pathology) {
		Map<Long, String> mapCombinedTreatment = new HashMap<>();
		List<String> combinedTreatmentList = new ArrayList<>();
		List<PatientTreatment> patientTreatmentList = patientTreatmentRepository.findPatientTreatmentByCombinedTreatment()
				.stream().filter(patientTreatment -> patientTreatment.getPatientDiagnose().getPatient().getPathologies().contains(pathology)).collect(Collectors.toList());

		// Se obtiene un mapa de pacientes y tipos de tratamientos concatenados
		for(PatientTreatment pt:patientTreatmentList){
			if(mapCombinedTreatment.containsKey(pt.getPatientDiagnose().getPatient().getId())){
				String lbl = mapCombinedTreatment.get(pt.getPatientDiagnose().getPatient().getId()) + " + " + pt.getType();
				mapCombinedTreatment.replace(pt.getPatientDiagnose().getPatient().getId(), lbl);
			}else{
				mapCombinedTreatment.put(pt.getPatientDiagnose().getPatient().getId(), pt.getType());
			}
		}

		//Se crea una lista con los resultados de los pacientes con los distintos tratamientos a consultar
		for (Map.Entry<Long, String>entry:mapCombinedTreatment.entrySet()){
			String[] typesTreatments = entry.getValue().replace(" ","").split("\\+");
			fillCombinedTreatmentList(combinedTreatmentList, typesTreatments);
		}

		return combinedTreatmentList;
	}

	private void fillCombinedTreatmentList(List<String> combinedTreatmentList, String[] typesTreatments) {
		List<String> tratamientos = Arrays.asList(typesTreatments);

		if(typesTreatments.length == 2){
			obtainTreatmentCombined(combinedTreatmentList, typesTreatments);

		} else if ( typesTreatments.length > 2 ){
			if (tratamientos.contains(TREATMENT_TYPE_BIOLOGICO)){
				combinedTreatmentList.add(TREATMENT_TYPE_BIOLOGICO);
			} else if ( tratamientos.contains(TREATMENT_TYPE_TOPICO)
					&& tratamientos.contains(TREATMENT_TYPE_QUIMICO)
					&& tratamientos.contains(TREATMENT_TYPE_FOTOTERAPIA) ){
				combinedTreatmentList.add(TREATMENT_TYPE_TOPICO_FOTOTERAPIA_QUIMICO);
			}
		}
	}

	private void obtainTreatmentCombined(List<String> combinedTreatmentList, String[] typesTreatments) {
		if(TREATMENT_TYPE_TOPICO_FOTOTERAPIA.contains(typesTreatments[0].toUpperCase())
				&& TREATMENT_TYPE_TOPICO_FOTOTERAPIA.contains(typesTreatments[1].toUpperCase())){
			combinedTreatmentList.add(TREATMENT_TYPE_TOPICO_FOTOTERAPIA);
		} else if(TREATMENT_TYPE_TOPICO_QUIMICO.contains(typesTreatments[0].toUpperCase())
				&& TREATMENT_TYPE_TOPICO_QUIMICO.contains(typesTreatments[1].toUpperCase())){
			combinedTreatmentList.add(TREATMENT_TYPE_TOPICO_QUIMICO);
		} else if(TREATMENT_TYPE_BIOLOGICO_FOTOTERAPIA.contains(typesTreatments[0].toUpperCase())
				&& TREATMENT_TYPE_BIOLOGICO_FOTOTERAPIA.contains(typesTreatments[1].toUpperCase())){
			combinedTreatmentList.add(TREATMENT_TYPE_BIOLOGICO_FOTOTERAPIA);
		} else if(TREATMENT_TYPE_QUIMICO_FOTOTERAPIA.contains(typesTreatments[0].toUpperCase())
				&& TREATMENT_TYPE_QUIMICO_FOTOTERAPIA.contains(typesTreatments[1].toUpperCase())){
			combinedTreatmentList.add(TREATMENT_TYPE_QUIMICO_FOTOTERAPIA);
		} else if(TREATMENT_TYPE_BIOLOGICO_QUIMICO.contains(typesTreatments[0].toUpperCase())
				&& TREATMENT_TYPE_BIOLOGICO_QUIMICO.contains(typesTreatments[1].toUpperCase())){
			combinedTreatmentList.add(TREATMENT_TYPE_BIOLOGICO_QUIMICO);
		}
	}

	private Map<Patient, Long> fillPatientTreatmentMapByNumberChangesOfBiologicalTreatment(Pathology pathology) {
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByNumberChangesOfBiologicTreatment().stream().filter(patientTreatment -> patientTreatment.getPatientDiagnose().getPatient().getPathologies().contains(pathology)).collect(Collectors.toList());
		Map<Patient, Long> patientsMaps = patientTreatmentList.stream()
				.collect(groupingBy(pt -> pt.getPatientDiagnose().getPatient(), Collectors.counting()));

		List<PatientTreatment> patientTreatmentWithoutChangesList = patientTreatmentRepository.findPatientTreatmentByNoChangesBiologicTreatment()
				.stream().filter(patientTreatment -> patientTreatment.getPatientDiagnose().getPatient().getPathologies().contains(pathology)).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(patientTreatmentWithoutChangesList)) {
			for (PatientTreatment pt:patientTreatmentWithoutChangesList){
				if (!patientsMaps.containsKey(pt.getPatientDiagnose().getPatient())) {
					patientsMaps.put(pt.getPatientDiagnose().getPatient(), 0L);
				}
			}
		}


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

	private Function<PatientTreatment, String> functionDescriptionMedicineByTreatment() {
		return pt->{
			return pt.getMedicine()!=null?pt.getMedicine().getActIngredients():"";
		};
	}

	public void save(PatientTreatment patientTreatment) {
		patientTreatmentRepository.saveAndFlush(patientTreatment);
	}
}
