package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.service.RoleService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Controlador de roles", tags = "role")
@Log4j2
@RestController
@RequestMapping(RoleController.ROLE_MAPPING)
public class RoleController {

	static final String ROLE_MAPPING = "/roles";
	static final String ROLE_LIST_MAPPING = "/all";
	private static final String CALLING_SERVICE = "Calling service from controller...";

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@ApiOperation("Recuperar todos los roles")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<RoleDTO> getAllRoles(@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return roleService.getAllRoles(pageable);
	}

	@ApiOperation("Recuperar todos los roles")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(ROLE_LIST_MAPPING)
	public List<RoleDTO> getAllRoles() {
		log.debug(CALLING_SERVICE);
		return roleService.getAllRoles();
	}

	@ApiOperation("Recuperar un rol por identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public RoleDTO getOneRole(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		log.debug(CALLING_SERVICE);
		return roleService.getOneRoleById(id);
	}

	@ApiOperation("Buscador de roles")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/searches")
	public Page<RoleDTO> findRolesBySearch(@ApiParam(value = "buscador")
										   @RequestParam(required = false, defaultValue = "") final String name,
										   @PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return roleService.findRolesBySearch(name, pageable);
	}

	@ApiOperation("Recuperar el menu/secciones de un rol por el identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/menus/{id}")
	public MenuDTO getMenuByRoleName(@ApiParam(value = "identificador", required = true) @PathVariable Long id)
			throws ServiceException {
		return roleService.getMenuByRole(id);
	}

	@ApiOperation("Añadir un nuevo rol")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public RoleDTO addRole(@RequestBody @Valid final RoleDTO roleDTO) {
		log.debug(CALLING_SERVICE);
		return roleService.addRole(roleDTO);
	}

	@ApiOperation("Modificar un rol")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public RoleDTO updateRole(@RequestBody @Valid final RoleDTO roleDTO) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return roleService.updateRole(roleDTO);
	}

	@ApiOperation("Eliminar un rol por el identificador")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteRole(@ApiParam(value = "idenitifacor", required = true) @PathVariable final Long id)
			throws ServiceException {
		log.debug(CALLING_SERVICE);
		roleService.deleteRole(id);
	}
}
