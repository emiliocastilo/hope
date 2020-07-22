/**
 *
 */
package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.IndicationDTO;
import es.plexus.hopes.hopesback.repository.IndicationRepository;
import es.plexus.hopes.hopesback.repository.model.Indication;
import es.plexus.hopes.hopesback.service.mapper.IndicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author jose.estevezbarroso
 *
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class IndicationService {

	private static final String CALLING_DB = "Llamando a la DB...";

	private final IndicationRepository indicationRepository;

	public IndicationDTO getIndicationByDescription(final String description) {
		log.debug(CALLING_DB);
		Indication indication = indicationRepository.findByDescription(description).orElse(null);
		return IndicationMapper.INSTANCE.entityToDto(indication);
	}
}
