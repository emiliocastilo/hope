package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.repository.PathologyRepository;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PathologyService {

	private final PathologyRepository pathologyRepository;

	@Autowired
	public PathologyService(final PathologyRepository pathologyRepository) {
		this.pathologyRepository = pathologyRepository;
	}

	Optional<Pathology> getOnePathologyById(final Long id) {
		return pathologyRepository.findById(id);
	}
}
