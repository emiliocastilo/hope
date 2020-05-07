package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.RequestPasswordChangeDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.repository.model.Token;
import es.plexus.hopes.hopesback.service.UserService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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
	public List<UserDTO> getAllUsers() {
		log.info("Get all users");
		return userService.getAllUsers();
	}

	@ApiOperation("Recuperar un usuario por identificador")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public UserDTO getOneUserById(@ApiParam(value = "identificador", required = true) @PathVariable final Long id) {
		return userService.getOneUserById(id);
	}

	@ApiOperation("Crear un nuevo usuario")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UserDTO saveUser(@RequestBody @Valid final UserDTO user) throws ServiceException {
		return userService.addUser(user);
	}

	@ApiOperation("Actualizar el token al confirmar el rol con el que quiere acceder")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@PostMapping("/choose-profiles")
	public UserSimpleDTO chooseProfile(@RequestBody final String role,
									   final Authentication authentication, final HttpServletResponse response) {
		String token = TokenProvider.generateToken(authentication.getName(), role, SECOND_TOKEN_EXPIRATION_TIME);
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.getOneSimpleUserByName(userName, role);
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
