package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static es.plexus.hopes.hopesback.configuration.security.Constants.HEADER_AUTHORIZACION_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SECOND_TOKEN_EXPIRATION_TIME;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_BEARER_PREFIX;

@RestController
@RequestMapping(UserController.USER_MAPPING)
public class UserController {

	static final String USER_MAPPING = "/user";

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	private final UserService userService;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDTO> getAllUsers() {
		LOGGER.info("Get all users");
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable Long id) {
		return userService.getOneUserById(id);
	}

	@GetMapping("/{username}")
	public UserDTO getUser(@PathVariable String username) {
		return userService.getOneUserByName(username);
	}

	@PostMapping
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO user) {
		final UserDTO userDTO = userService.addUser(user);

		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(userDTO.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping("/choose_profile")
	public void chooseProfile(@RequestBody String role, Authentication authentication, HttpServletResponse response) {
		String token = TokenProvider.generateToken(authentication.getName(), role, SECOND_TOKEN_EXPIRATION_TIME);
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
	}

}
