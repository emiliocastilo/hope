package es.plexus.hopes.hopesback.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.GraphHealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.repository.HealthOutcomeRepository;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.service.mapper.HealthOutcomeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class HealthOutcomeService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final HealthOutcomeRepository healthOutcomeRepository;
	
	private final PatientService patientService;

	public Map<String, Long> findResultsByTypes(final String type) {
		log.debug(CALLING_DB);
		return fillResultsByTypes(type);
	}

	public Page<GraphPatientDetailDTO> getDetailsResultsByType(final String indexType, final Pageable pageable) {
		log.debug(CALLING_DB);		
		return  healthOutcomeRepository.getDetailsResultsByType(indexType, pageable);	
	}
	
	public List<GraphPatientDetailDTO> getDetailsResultsByType(final String indexType) {
		log.debug(CALLING_DB);		
		return  healthOutcomeRepository.getDetailsResultsByType(indexType);	
	}
	
	public List<Long> getAllPatientsId() {
		log.debug(CALLING_DB);		
		return  healthOutcomeRepository.getAllPatientsId();	
	}
	
	private Map<String, Long> fillResultsByTypes(String type) {
		Map<String, Long> result = new HashMap<>();

		List<HealthOutcome> healthoutcomeResultList = healthOutcomeRepository.findResultsByTypes(type);
		
		Map<Patient, HealthOutcome> mapPatientMaxDate = healthoutcomeResultList.stream()
        .collect(Collectors.toMap(
        		HealthOutcome::getPatient,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(HealthOutcome::getDate))));
		
		mapPatientMaxDate.entrySet().stream().forEach(m -> {			
			long count = healthoutcomeResultList
				.stream()
				.filter(h -> (h.getPatient().equals(m.getKey()) && (h.getDate().equals(m.getValue().getDate()))))
				.count();
			result.put(m.getValue().getResult(), count);
		});
		
		return result;
	}

	public Map<String,List<GraphHealthOutcomeDTO>> findEvolutionClinicalIndicesByIndexTypeAndPatient(String indicesTypes, Long patId) {

		Map <String,List<GraphHealthOutcomeDTO>> mapEvolutionIndicesGraph = new HashMap<>();

		Arrays.stream(indicesTypes.replace(" ","").split(",")).forEach(indexType ->{

			List<HealthOutcome> healthoutcomeResultList = healthOutcomeRepository
					.findEvolutionClinicalIndicesByIndexTypeAndPatient(indexType, patId);
			mapEvolutionIndicesGraph.put(indexType,healthoutcomeResultList.stream()
					.map(Mappers.getMapper(HealthOutcomeMapper.class)::entityToGraphHealthOutcomeDto)
					.collect(Collectors.toList()));
		});

		return mapEvolutionIndicesGraph;
	}

	public List<HealthOutcomeDTO> saveScoreDataByIndexType(List<HealthOutcomeDTO> healthOutcomeDtos) {
		log.debug(CALLING_DB);		
		List<HealthOutcome> healthOutcomesSaved = new ArrayList<>();
		healthOutcomeDtos.stream().forEach(ho -> { 
			HealthOutcome healthOutcome = HealthOutcomeMapper.INSTANCE.dtoToEntity(ho);
			healthOutcome.setPatient(patientService.getEntityPatientById(ho.getPatientId()).orElse(null));
			HealthOutcome entitySaved = healthOutcomeRepository.saveAndFlush(healthOutcome);
			healthOutcomesSaved.add(entitySaved);
		});
		
		return HealthOutcomeMapper.INSTANCE.entityToHealthOutcomeDtos(healthOutcomesSaved);
	}
}
