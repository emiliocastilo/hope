package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.EMAIL_VIOLATION_CONSTRAINT_MESSAGE;

@Log4j2
@Service
public class ProService {

	private final UserService userService;

	@Value("${pros.url}")
	private String prosUrl;

	public ProService(UserService userService) {
		this.userService = userService;
	}

	public String getIndexPage() throws ServiceException {
		log.info("ProService.updatePass.getIndexPage");
		return "index";

	}

	public UserSimpleDTO getOneSimpleUserByUsername(final String username, String roleCode, Collection<? extends GrantedAuthority> authorities) {
		log.info("ProService.getOneSimpleUserByUsername");
		return userService.getOneSimpleUserByUsername(username,roleCode,authorities);

	}

	public ResponseEntity updatePassword(PasswordDTO passwordDto) throws ServiceException {
		log.info("ProService.updatePassword");
		userService.updatePassword(passwordDto);
		return ResponseEntity.status(HttpStatus.OK).body("Password updated");

	}

	public ResponseEntity updateEmail(UserDTO userDto) throws ServiceException {
		log.info("ProService.updateEmail");
		final String newEmail = userDto.getEmail();
		userService.findUserByUsername(userDto);
		if (userDto.getEmail().equalsIgnoreCase(newEmail)){
			return ResponseEntity.status(ServiceExceptionCatalog.EMAIL_VIOLATION_CONSTRAINT_EXCEPTION.getHttpStatus())
					.body(EMAIL_VIOLATION_CONSTRAINT_MESSAGE);
		}
		userDto.setEmail(newEmail);
		userService.updateUser(userDto);

		return ResponseEntity.status(HttpStatus.OK).body("EMail updated");
	}

	public List getPatientPro(UserDTO userDto) throws ServiceException {
		log.info("ProService.getPatientPro");
		return new ArrayList<>();

	}

	public List getPatientPros(UserDTO userDto) throws ServiceException {
		log.info("ProService.getPatientPros");
		return new ArrayList<>();

	}


}
