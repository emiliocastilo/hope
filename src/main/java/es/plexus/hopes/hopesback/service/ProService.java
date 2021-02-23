package es.plexus.hopes.hopesback.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.EMAIL_VIOLATION_CONSTRAINT_MESSAGE;

@Log4j2
@Service
public class ProService {

	private final UserRepository photoRepository;
	private final PatientService patientService;
	private final UserService userService;

	@Value("${pros.url}")
	private String prosUrl;

	public ProService(UserRepository userRepository, PatientService patientService, UserService userService) {
		this.photoRepository = userRepository;
		this.patientService = patientService;
		this.userService = userService;
	}
	public String getIndexPage() throws ServiceException {
		log.info("ProService.updatePass.getIndexPage");
		//final String endPoint = prosUrl.concat("?token=").concat(qrToken.replace("Bearer ", ""));
		return "index";
	}

	public UserSimpleDTO getOneSimpleUserByUsername(final String username, String roleCode, Collection<? extends GrantedAuthority> authorities) {
		log.info("ProService.getOneSimpleUserByUsername");
		return userService.getOneSimpleUserByUsername(username,roleCode,authorities);
	}

	public String updatePassword_(String user) throws ServiceException {
		log.info("ProService.updatePass");
		JSONArray list = new JSONArray(user);
		String token = (String) list.get(0);
		JSONObject userJson = (JSONObject) list.get(1);;
		String username =  userJson.getString("username");
		String password =  userJson.getString("password");
		String newPassword =  userJson.getString("newPassword");
		PasswordDTO pass = new PasswordDTO();
		pass.setPassword(password);
		pass.setNewPassword(newPassword);
		pass.setToken(token);
		/*String mail =  userJson.getString("mail");
		//String userProStr =  userJson.getString("userPro");
		JSONObject userPro = userJson.getJSONObject("userPro");
		String username =  userPro.getString("username");
		String password =  userJson.getString("password");*/
		userService.updatePassword(pass);
		/*
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonStr = mapper.writeValueAsString(user);
			JSONArray array = new JSONArray(user);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//final String endPoint = prosUrl.concat("?token=").concat(qrToken.replace("Bearer ", ""));

		return "OK";
	}

	public String updatePassword(PasswordDTO passwordDto) throws ServiceException {

		log.info("ProService.updatePassword");
		userService.updatePassword(passwordDto);
		return "OK";

	}

	public String updateEmail_(String user) throws ServiceException {

		log.info("ProService.updateEmail_");
		JSONArray list = new JSONArray(user);
		String token = (String) list.get(0);
		JSONObject userJson = (JSONObject) list.get(1);;
		String username =  userJson.getString("username");
		String email =  userJson.getString("email");

		UserDTO userPro = new UserDTO();
		userPro.setEmail(email);
		userPro.setUsername(username);
		userService.updateUser(userPro);
		return "OK";
	}
	public ResponseEntity updateEmail(UserDTO userDto) throws ServiceException {
		List<String> errors = new ArrayList<String>();
		log.info("ProService.updateEmail");
		final String newEmail = userDto.getEmail();
		userService.findUserByUsername(userDto);
		if (userDto.getEmail().equalsIgnoreCase(newEmail)){
			//response.status(ServiceExceptionCatalog.EMAIL_VIOLATION_CONSTRAINT_EXCEPTION.getHttpStatus());
			//response.body(ServiceExceptionCatalog.EMAIL_VIOLATION_CONSTRAINT_EXCEPTION.getMessage());
			return ResponseEntity.status(ServiceExceptionCatalog.EMAIL_VIOLATION_CONSTRAINT_EXCEPTION.getHttpStatus())
					.body(EMAIL_VIOLATION_CONSTRAINT_MESSAGE);

		}
		userService.updateUser(userDto);
		log.info("ProService.updateUser");
		return ResponseEntity.status(HttpStatus.OK).body("EMail updated");
	}

	public List getPatientPro(UserDTO userDto) throws ServiceException {

		log.info("ProService.getPatientPro");
		//final String endPoint = prosUrl.concat("?token=").concat(qrToken.replace("Bearer ", ""));
		return new ArrayList<>();

	}

	public List getPatientPros(UserDTO userDto) throws ServiceException {

		log.info("ProService.getPatientPros");
		//final String endPoint = prosUrl.concat("?token=").concat(qrToken.replace("Bearer ", ""));
		return new ArrayList<>();
	}


}
