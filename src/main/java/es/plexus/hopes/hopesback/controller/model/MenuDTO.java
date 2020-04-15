package es.plexus.hopes.hopesback.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {

    private Integer id;
    private Integer order;
    private String title;
    private String icon;
    private String url;
    private List<MenuDTO> children;
}
