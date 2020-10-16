package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.RequestPasswordChangeDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.controller.model.UserUpdateDTO;
import es.plexus.hopes.hopesback.repository.model.Token;
import es.plexus.hopes.hopesback.service.UserService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

import static es.plexus.hopes.hopesback.configuration.security.Constants.HEADER_AUTHORIZACION_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SECOND_TOKEN_EXPIRATION_TIME;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_BEARER_PREFIX;

@Api(value = "Controlador de usuarios", tags = "user")
@RestController
@Log4j2
@RequestMapping(UserController.USER_MAPPING)
public class UserController {

	static final String USER_MAPPING = "/users";
	private final UserService userService;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@ApiOperation("Recuperar todos los usuarios")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<UserDTO> getAllUsers(
			@ApiParam(value = "ID role usuario loggeado")
			@RequestParam(value = "idRoleSelected") Long idRoleSelected,
			@PageableDefault(size = 5) Pageable pageable) {
		log.info("Get all users");
		return userService.getAllUsers(idRoleSelected, pageable);
	}

	@ApiOperation("Recuperar un usuario por identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public UserDTO getOneUserById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		return userService.getOneUserById(id);
	}

	@ApiOperation("Buscador de usuarios")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/searches")
	public Page<UserDTO> findUsersBySearch(
			@ApiParam(value = "buscador")
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug("Get users by filter...");
		return userService.findUsersBySearch(search, pageable);

	}

	@ApiOperation("Filtro de usuarios")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/filters")
	public Page<UserDTO> filterUsers(
			@ApiParam(value = "filtrado")
			@RequestParam(value = "user", required = false, defaultValue = "{}") String user,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug("Filter...");
		return userService.filterUsers(user, pageable);
	}

	@ApiOperation("Crear un nuevo usuario")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UserDTO saveUser(@RequestBody @Valid final UserDTO user) throws ServiceException {
		return userService.addUser(user);
	}

	@ApiOperation("Actualizar un usuario")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public UserDTO updateUser(@RequestBody @Valid final UserUpdateDTO userUpdateDTO) {

		if (userService.getOneUserById(userUpdateDTO.getId()) == null) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception("Usuario no encontrado con id=" + userUpdateDTO.getId());
		}

		log.debug("Llamando al servicio...");
		return userService.updateUser(userUpdateDTO);
	}

	@ApiOperation("Borrar un usuario por identificador")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteUser(
			@ApiParam(value = "identificador") @PathVariable final Long id) {

		if (userService.getOneUserById(id) == null) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception("Usuario no encontrado con id=" + id);
		}

		log.debug("Llamando al servicio...");
		userService.deleteUser(id);
	}

	@ApiOperation("Actualizar el token al confirmar el rol con el que quiere acceder")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@PostMapping("/choose-profiles")
	public UserSimpleDTO chooseProfile(@RequestBody final String role,
									   final Authentication authentication, final HttpServletResponse response) {
		String token = TokenProvider.generateToken(authentication.getName(), role, SECOND_TOKEN_EXPIRATION_TIME);
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return userService.getOneSimpleUserByUsername(userName, role, authorities);
	}

	@ApiOperation("Solicitud para cambiar la contraseña")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PostMapping("/request-password-changes")
	public void requestPasswordChange(@Valid @RequestBody final RequestPasswordChangeDTO request)
			throws ServiceException {
		log.info("Request password change");

		userService.requestPasswordChange(request.getEmail());
	}

	@ApiOperation("Cabio de contraseña por token")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/reset-passwords")
	public String resetPassword(@ApiParam(value = "token") @RequestParam("token") final String token) {
		log.info("Reset password");

		Token result = userService.resetPassword(token);
		if (result != null) {
			return "redirect:/updatePassword.html";
		} else {
			return "redirect:/login.html";
		}
	}

	@ApiOperation("Guardar nueva contraseña")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/save-new-passwords")
	public String saveNewPassword(@Valid @RequestBody final PasswordDTO passwordDTO) throws ServiceException {
		log.info("Save new password (for forget password)");
		return userService.saveNewPassword(passwordDTO);
	}

	@ApiOperation("Actualizar contraseña")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/update-passwords")
	public String updatePassword(@Valid @RequestBody final PasswordDTO passwordDTO) throws ServiceException {
		log.info("Update new password (for logged users)");
		return userService.updatePassword(passwordDTO);
	}

}
