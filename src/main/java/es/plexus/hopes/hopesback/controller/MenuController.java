package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.repository.model.ERole;
import es.plexus.hopes.hopesback.service.MenuService;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    private static final Logger LOGGER = LogManager.getLogger(MenuController.class);

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuDTO> getMenuByRole(@RequestParam ERole role) {

        menuService.findMenuByRole(role);

        return null;
    }

}
