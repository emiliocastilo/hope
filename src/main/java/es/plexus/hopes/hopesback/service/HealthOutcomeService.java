package es.plexus.hopes.hopesback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.repository.HealthOutcomeRepository;
import es.plexus.hopes.hopesback.service.mapper.DetailGraphDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class HealthOutcomeService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final HealthOutcomeRepository healthOutcomeRepository;

	
	public List<HealthOutcomeDTO> findResultsByTypes(final String type) {
		log.debug(CALLING_DB);
		return healthOutcomeRepository.findResultsByTypes(type);
	}

	public Page<DetailGraphDTO> detailsResults(final String type, final Pageable pageable) {
		log.debug(CALLING_DB);
		
		Page<Object[]>detailGraph = healthOutcomeRepository.detailsResults(type, pageable);
		return detailGraph.map(DetailGraphDTOMapper.INSTANCE::objectToDetailGraphDTOConventer); 
	}
}
