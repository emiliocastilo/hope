/**
 *
 */
package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.IndicationDTO;
import es.plexus.hopes.hopesback.repository.IndicationRepository;
import es.plexus.hopes.hopesback.repository.RoleRepository;
import es.plexus.hopes.hopesback.repository.model.Indication;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.service.mapper.IndicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	private final RoleRepository roleRepository;

	public IndicationDTO getIndicationByDescription(final String description) {
		log.debug(CALLING_DB);
		Indication indication = indicationRepository.findByDescription(description).orElse(null);
		return IndicationMapper.INSTANCE.entityToDto(indication);
	}

	public List<IndicationDTO> findAll(String token) {
		log.debug(CALLING_DB);
		String roleCode = TokenProvider.getRoleSelected(token);
		Optional<Role> role = roleRepository.findByCode(roleCode);
		List<Indication> indicationList = new ArrayList<>();

		if (role.isPresent()) {
			indicationList = indicationRepository.findByPathologyId(role.get().getPathology().getId());
		}
		
		return indicationList.stream().map(IndicationMapper.INSTANCE::entityToDto).collect(Collectors.toList());
	}
}
