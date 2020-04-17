package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.repository.SectionRepository;
import es.plexus.hopes.hopesback.repository.model.ERole;
import es.plexus.hopes.hopesback.repository.model.Section;
import es.plexus.hopes.hopesback.service.mapper.MenuMapper;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final SectionRepository sectionRepository;

    public MenuService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public MenuDTO findMenuByRole(ERole role) {

        MenuDTO tree = new MenuDTO();

        List<Section> sections = sectionRepository.findByMenuTrue();

        if (!sections.isEmpty()) {
            buildTree(sections, tree);
        }

        return tree;
    }

    private void buildTree(List<Section> sections, MenuDTO parent) {

        // Copy list
        List<Section> remainingSections = new ArrayList<>(sections);

        for (Section section : sections) {

            // Root
            if (section.getFatherSection() == null) {

                parent.setId(section.getId());
                parent.setTitle(section.getTitle());
                parent.setIcon(section.getIcon());
                parent.setUrl(section.getUrl());
                parent.setOrder(section.getOrder().intValue());
                parent.setChildren(new ArrayList<>());

                remainingSections.remove(section);
                buildTree(remainingSections, parent);
                break;
            }
            // Child
            else if (section.getFatherSection().getId() == parent.getId()) {

                List<MenuDTO> childrens = parent.getChildren();

                // New child
                MenuDTO children = MenuMapper.INSTANCE.entityToDto(section);
                children.setChildren(new ArrayList<>());

                // Assign child to parent
                childrens.add(children);
                parent.setChildren(childrens);

                // depth search
                remainingSections.remove(section);
                buildTree(remainingSections, children);
            }
        }
    }

}
