package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.SectionDTO;
import es.plexus.hopes.hopesback.repository.SectionRepository;
import es.plexus.hopes.hopesback.repository.model.Section;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
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

	private static final String CALLING_DB = "Llamando a la DB...";
	private static final String NOT_FOUND_ID = "Not found section with id=";

	private final SectionRepository sectionRepository;

	public SectionDTO save(SectionDTO sectionDTO) {
		Section section = SectionMapper.INSTANCE.dtoToEntity(sectionDTO);
		log.debug(CALLING_DB);
		return SectionMapper.INSTANCE.entityToDto(sectionRepository.save(section));
	}

	public SectionDTO findById(Long id) {
		SectionDTO sectionDto = null;
		log.debug(CALLING_DB);
		Section section = sectionRepository.findById(id).orElse(null);
		if(Objects.nonNull(section)) {
			sectionDto = Optional.of(SectionMapper.INSTANCE.entityToDto(section)).get();
		}
		return sectionDto;
	}

	public SectionDTO deleteById(Long id) throws ServiceException {
		log.debug(CALLING_DB);
		Section section = sectionRepository.findById(id).orElse(null);
		if(Objects.isNull(section)) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_ID + id);
		}
		section.setActive(false);
		return SectionMapper.INSTANCE.entityToDto(sectionRepository.save(section));
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
