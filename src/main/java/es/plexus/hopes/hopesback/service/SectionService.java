package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.SectionDTO;
import es.plexus.hopes.hopesback.repository.SectionRepository;
import es.plexus.hopes.hopesback.repository.model.Section;
import es.plexus.hopes.hopesback.service.mapper.SectionMapper;
import es.plexus.hopes.hopesback.service.utils.MenuUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static es.plexus.hopes.hopesback.service.Constants.ROLE_ADMIN;

@Log4j2
@Service
@RequiredArgsConstructor
public class SectionService {

	private static final String CALLING_DB = "Calling DB...";

	private final SectionRepository sectionRepository;

	public SectionDTO save(SectionDTO sectionDTO) {
		Section section = SectionMapper.INSTANCE.dtoToEntity(sectionDTO);
		log.debug(CALLING_DB);
		return SectionMapper.INSTANCE.entityToDto(sectionRepository.save(section));
	}

	public SectionDTO findById(Long id) {
		SectionDTO sectionDto = null;
		log.debug(CALLING_DB);
		Section dispensationDetail = sectionRepository.findById(id).orElse(null);
		if(Objects.nonNull(dispensationDetail)) {
			sectionDto = Optional.of(SectionMapper.INSTANCE.entityToDto(dispensationDetail)).get();
		}
		return sectionDto;
	}

	public void deleteById(Long id) {
		log.debug(CALLING_DB);
		sectionRepository.deleteById(id);
	}

	public MenuDTO findAllSections() {
		MenuDTO tree = new MenuDTO();

		List<Section> sections = sectionRepository.findSectionsByName(ROLE_ADMIN);

		if (!sections.isEmpty()) {
			MenuUtils.buildTree(sections, tree);
		}

		return tree;
	}


}
