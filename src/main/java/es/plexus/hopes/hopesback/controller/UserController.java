package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.RequestPasswordChangeDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.repository.model.Token;
import es.plexus.hopes.hopesback.service.UserService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static es.plexus.hopes.hopesback.configuration.security.Constants.HEADER_AUTHORIZACION_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SECOND_TOKEN_EXPIRATION_TIME;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_BEARER_PREFIX;

@RestController
@Log4j2
@RequestMapping(UserController.USER_MAPPING)
public class UserController {

	static final String USER_MAPPING = "/user";

	private final UserService userService;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDTO> getAllUsers() {
		log.info("Get all users");
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserDTO getOneUserById(@PathVariable Long id) {
		return userService.getOneUserById(id);
	}

	@PostMapping
	public UserDTO saveUser(@RequestBody @Valid UserDTO user) throws ServiceException {
		return userService.addUser(user);
	}

	@PostMapping("/choose_profile")
	public UserSimpleDTO chooseProfile(@RequestBody String role, Authentication authentication, HttpServletResponse response) {
		String token = TokenProvider.generateToken(authentication.getName(), role, SECOND_TOKEN_EXPIRATION_TIME);
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.getOneSimpleUserByName(userName, role);
	}

	@PostMapping("/request_password_change")
	public ResponseEntity requestPasswordChange(@Valid @RequestBody RequestPasswordChangeDTO request) throws ServiceException {
		log.info("Request password change");

		userService.requestPasswordChange(request.getEmail());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/reset_password")
	public String resetPassword(@RequestParam("token") String token) {
		log.info("Reset password");

	    Token result = userService.resetPassword(token);
	    if(result != null) {
	    	 return "redirect:/updatePassword.html";
	    } else {
	        return "redirect:/login.html";
	    }
	}

	@PostMapping("/save_new_password")
	public String saveNewPassword(@Valid  @RequestBody PasswordDTO passwordDTO) throws ServiceException {
		log.info("Save new password (for forget password)");
		return userService.saveNewPassword(passwordDTO);
	}

	@PostMapping("/update_password")
	public String updatePassword(@Valid  @RequestBody PasswordDTO passwordDTO) throws ServiceException {
		log.info("Update new password (for logged users)");
		return userService.updatePassword(passwordDTO);
	}

}
