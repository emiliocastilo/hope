package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
	
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
}
