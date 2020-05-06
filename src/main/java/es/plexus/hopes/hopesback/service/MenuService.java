package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.repository.SectionRepository;
import es.plexus.hopes.hopesback.repository.model.Section;
import es.plexus.hopes.hopesback.service.utils.MenuUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final SectionRepository sectionRepository;

    public MenuService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public MenuDTO findMenuByRole(List<String> roles) {

        MenuDTO tree = new MenuDTO();

        List<Section> sections = sectionRepository.findByMenuTrueAndRoles(roles);

        if (!sections.isEmpty()) {
            MenuUtils.buildTree(sections, tree);
        }

        return tree;
    }



}
