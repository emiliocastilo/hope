package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static es.plexus.hopes.hopesback.configuration.security.Constants.HEADER_AUTHORIZACION_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SECOND_TOKEN_EXPIRATION_TIME;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_BEARER_PREFIX;

@RestController
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/user/")
	public void saveUser(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@GetMapping("/user/")
	public List<User> getAllUsers() {

		LOGGER.info("Get all users");

		return userRepository.findAll();
	}

	@GetMapping("/user/{username}")
	public User getUser(@PathVariable String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@PostMapping("/user/choose_profile")
	public void chooseProfile(@RequestBody String role, Authentication authentication, HttpServletResponse response) {
		String token = TokenProvider.generateToken(authentication.getName(), role, SECOND_TOKEN_EXPIRATION_TIME);
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
	}

}
