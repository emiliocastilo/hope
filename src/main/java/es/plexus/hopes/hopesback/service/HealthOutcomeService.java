package es.plexus.hopes.hopesback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.repository.HealthOutcomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class HealthOutcomeService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final HealthOutcomeRepository healthOutcomesRepository;

	public List<HealthOutcomeDTO> getHealthOutcomesByType(String type) {
		
		log.debug(CALLING_DB);
		return healthOutcomesRepository.getHealthOutcomesByType(type);
	}

}
