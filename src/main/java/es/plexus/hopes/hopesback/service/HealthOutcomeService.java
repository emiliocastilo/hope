package es.plexus.hopes.hopesback.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
import es.plexus.hopes.hopesback.repository.HealthOutcomeRepository;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.service.mapper.DetailGraphDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class HealthOutcomeService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final HealthOutcomeRepository healthOutcomeRepository;

	
	public Map<String, Long> findResultsByTypes(final String type) {
		log.debug(CALLING_DB);
		return fillResultsByTypes(type);
	}

	public Page<DetailGraphDTO> getDetailsResultsByType(final String indexType, final Pageable pageable) {
		log.debug(CALLING_DB);
		
		Page<PatientTreatment>detailResultsByTypeList = healthOutcomeRepository.getDetailsResultsByType(indexType, pageable);
		Page<DetailGraphDTO> mapTreatment = detailResultsByTypeList.map(DetailGraphDTOMapper.INSTANCE::patientTreatmentToDetailGraphDTOConventer);
		List<HealthOutcome> healthOutcomesToDate = healthOutcomeRepository.findResultsByTypesAndMaxDate(indexType);	
		Map<String, HealthOutcome> mapToDate = healthOutcomesToDate.stream().collect(Collectors.toMap(p -> p.getPatient().getNhc(), Function.identity()));
		
		
		for (DetailGraphDTO detailGraphDTO : mapTreatment) {
			if(mapToDate.containsKey(detailGraphDTO.getNhc())){
				HealthOutcome healthOutcome = mapToDate.get(detailGraphDTO.getNhc());
				if(indexType.equals("PASI")){
					detailGraphDTO.setDatePasi(healthOutcome.getDate());
					detailGraphDTO.setPasi(healthOutcome.getValue());
				} else if(indexType.equals("DLQI")){
					detailGraphDTO.setDateDlqi(healthOutcome.getDate());
					detailGraphDTO.setDlqi(healthOutcome.getValue());
				}				
			}
			
		}
		
		return mapTreatment;
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
}
