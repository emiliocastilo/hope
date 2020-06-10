/**
 * 
 */
package es.plexus.hopes.hopesback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.repository.IndicationRepository;
import es.plexus.hopes.hopesback.repository.model.Indication;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author jose.estevezbarroso
 *
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class IndicationService {
	
	private static final String CALLING_DB = "Llamando a la DB...";

	@Autowired
	private final IndicationRepository indicationRepository;
	
	public Indication getIndicationByDescription(final String description) {
		log.debug(CALLING_DB);
		return indicationRepository.findByDescription(description).orElse(null);
	}
}
