package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.service.RoleService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(RoleController.ROLE_MAPPING)
public class RoleController {

	static final String ROLE_MAPPING = "/role";
	private static final String CALLING_SERVICE = "Calling service from controller...";

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public List<RoleDTO> getAllRoles() {
		log.debug(CALLING_SERVICE);
		return roleService.getAllRoles();
	}

	@GetMapping("/{id}")
	public RoleDTO getOneRole(@PathVariable final Long id) {
		log.debug(CALLING_SERVICE);
		return roleService.getOneRoleById(id);
	}

	@GetMapping("/filter")
	public Page<RoleDTO> filterRole(@RequestParam(required = false, defaultValue = "") final String name,
									@PageableDefault(size = 5) Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return roleService.findRolesBySearch(name, pageable);
	}

	@GetMapping("/menu/{id}")
	public MenuDTO getMenuByRoleName(@PathVariable Long id) throws ServiceException {
		return roleService.getMenuByRole(id);
	}

	@PostMapping
	public RoleDTO addRole(@RequestBody @Valid final RoleDTO roleDTO) {
		log.debug(CALLING_SERVICE);
		return roleService.addRole(roleDTO);
	}

	@PutMapping
	public RoleDTO updateRole(@RequestBody @Valid final RoleDTO roleDTO) throws ServiceException {
		log.debug(CALLING_SERVICE);
		return roleService.updateRole(roleDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable final Long id) throws ServiceException {
		log.debug(CALLING_SERVICE);
		roleService.deleteRole(id);
	}
}
