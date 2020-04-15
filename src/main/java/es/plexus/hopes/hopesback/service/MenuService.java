package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.repository.SectionRepository;
import es.plexus.hopes.hopesback.repository.model.ERole;
import es.plexus.hopes.hopesback.repository.model.Section;
import es.plexus.hopes.hopesback.service.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final SectionRepository sectionRepository;

    public MenuService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<MenuDTO> findMenuByRole(ERole role) {

        List<Section> all = sectionRepository.findAll(); //TODO PRUEBA

        Section section = all.get(0);

        MenuDTO menuDTO = MenuMapper.INSTANCE.entityToDto(section);

        return null;
    }
}
