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
import java.util.Optional;

import static es.plexus.hopes.hopesback.service.Constants.ROLE_ADMIN;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.SECTION_ROOT_VIOLATION_CONSTRAINT_MESSAGE;

@Log4j2
@Service
@RequiredArgsConstructor
public class SectionService {

	private static final String CALLING_DB = "Llamando a la DB...";
	private static final String NOT_FOUND_ID = "No existe la sección con el id=";
	private static final String NOT_ROLE_ADMIN = "No existe ningún administrador";

	private final SectionRepository sectionRepository;

	public SectionDTO save(SectionDTO sectionDTO) {
		Section section = SectionMapper.INSTANCE.dtoToEntity(sectionDTO);
		log.debug(CALLING_DB);
		return SectionMapper.INSTANCE.entityToDto(sectionRepository.save(section));
	}

	public SectionDTO findById(Long id) throws ServiceException {
		SectionDTO sectionDto = null;
		log.debug(CALLING_DB);
		Optional<Section> section = sectionRepository.findById(id);
		if(!section.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_ID + id);
		}
		sectionDto = Optional.of(SectionMapper.INSTANCE.entityToDto(section.get())).get();
		return sectionDto;
	}

	public void deleteById(Long id) throws ServiceException {
		log.debug(CALLING_DB);
		Optional<Section> section = sectionRepository.findById(id);

		if (!section.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_ID + id);
		} else {
			Optional<Section> sectionsChildren = sectionRepository.findByFatherSection(section.get());
			if (sectionsChildren.isPresent()) {
				throw ServiceExceptionCatalog.SECTION_ROOT_VIOLATION_CONSTRAINT_EXCEPTION.exception(SECTION_ROOT_VIOLATION_CONSTRAINT_MESSAGE);
			}
		}
		sectionRepository.delete(section.get());
	}

	public MenuDTO findAllSections() throws ServiceException {
		MenuDTO tree = new MenuDTO();

		List<Section> sections = sectionRepository.findSectionsByRolName(ROLE_ADMIN);

		if (!sections.isEmpty()) {
			MenuUtils.buildTree(sections, tree);
		}else {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_ROLE_ADMIN);
		}

		return tree;
	}

    public SectionDTO findByFatherSectionIsNull() {
		Optional<Section> section = sectionRepository.findByFatherSectionIsNull();
		SectionDTO sectionDTO = null;
		if (section.isPresent()){
			sectionDTO = SectionMapper.INSTANCE.entityToDto(section.get());
		}
		return sectionDTO;
	}
}
