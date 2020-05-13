package es.plexus.hopes.hopesback.service.utils;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.repository.model.Section;
import es.plexus.hopes.hopesback.service.mapper.MenuMapper;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public final class MenuUtils {

    /**
     * Build tree menu with recursive iterations
     *
     * @param sections
     * @param parent
     */
    public static void buildTree(List<Section> sections, MenuDTO parent) {

        // Copy list
        List<Section> remainingSections = new ArrayList<>(sections);

        for (Section section : sections) {

            // Root
            if (section.getFatherSection() == null) {

                parent.setId(section.getId());
                parent.setTitle(section.getTitle());
                parent.setIcon(section.getIcon());
                parent.setUrl(section.getUrl());
                parent.setOrder(section.getOrder());
                parent.setActive(section.isActive());
                parent.setPrincipal(section.isActive());
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
                if (!childrens.contains(children)) {
                    childrens.add(children);
                    parent.setChildren(childrens);

                    // depth search
                    remainingSections.remove(section);
                    buildTree(remainingSections, children);
                }
            }
        }
    }

}
