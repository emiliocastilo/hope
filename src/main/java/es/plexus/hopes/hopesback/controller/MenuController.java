package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api("Controlador de los menus/secciones por rol")
@Log4j2
@RestController
@RequestMapping(MenuController.MENU_MAPPING)
public class MenuController {
	static final String MENU_MAPPING = "/menus";

	private final MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@ApiOperation("Recuperar el menu por el rol seleccionado en la autentificacion")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public MenuDTO getMenu(final Authentication authentication) {

		final List<String> roles = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return menuService.findMenuByRole(roles);
	}

}
