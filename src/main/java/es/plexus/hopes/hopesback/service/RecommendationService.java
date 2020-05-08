package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.RecommendationDTO;
import es.plexus.hopes.hopesback.repository.RecommendationRepository;
import es.plexus.hopes.hopesback.service.mapper.RecommendationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class RecommendationService {
	private static final String CALLING_DB = "Llamando a la DB...";

	private final RecommendationRepository recommendationRepository;

	public List<RecommendationDTO> findAllRecommendation() {
		log.debug(CALLING_DB);
		return recommendationRepository.findAll().stream().map(RecommendationMapper.INSTANCE::entityToDto)
				.collect(Collectors.toList());
	}

	public Optional<es.plexus.hopes.hopesback.repository.model.Recommendation> findById(final Long id) {
		log.debug(CALLING_DB);
		return recommendationRepository.findById(id);
	}
}
