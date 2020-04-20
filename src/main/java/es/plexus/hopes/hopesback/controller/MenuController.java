package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private static final Logger LOGGER = LogManager.getLogger(MenuController.class);

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public MenuDTO getMenu(Authentication authentication) {

        final List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        MenuDTO menu = menuService.findMenuByRole(roles);

        return menu;
    }

}
