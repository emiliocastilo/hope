package es.plexus.hopes.hopesback.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {

    private Long id;
    private Integer order;
    private String title;
    private String description;
    private String icon;
    private String url;
    private boolean active;
    private List<MenuDTO> children;
}
