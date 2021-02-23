package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.service.ProService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static es.plexus.hopes.hopesback.configuration.security.Constants.*;

@Api(value = "Controlador de pros", tags = "pro")
@Log4j2
@RestController
@RequestMapping(ProController.PRO_MAPPING)
public class ProController extends ResponseEntityExceptionHandler {

	static final String PRO_MAPPING = "/pro";
	private final ProService proService;

	@Autowired
	public ProController(final ProService proService) {
		this.proService = proService;
	}

	@ApiOperation("Actualizar el token al confirmar el rol con el que quiere acceder")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@PostMapping("/choose-profiles")
	public UserSimpleDTO chooseProfile(@RequestBody final String role, final Authentication authentication, final HttpServletResponse response) {
		log.info("ProController.pro/choose-profiles");
		String token = TokenProvider.generateToken(authentication.getName(), role, SECOND_TOKEN_EXPIRATION_TIME);
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return proService.getOneSimpleUserByUsername(userName, role, authorities);
	}

	@ApiOperation("Recuperar datos de pagina de pros del paciente")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/index")
	public ResponseEntity<String> getIndexPage() throws ServiceException {
		log.info("ProController.pro/index");
		String response =  proService.getIndexPage();
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}

	@ApiOperation("Actualizar contraseña de un paciente de pros")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/updatePassword")
	public String updatePassword(@Valid @RequestBody final PasswordDTO passwordDTO) throws ServiceException {
		log.info("ProController.pro/updatePassword");
		return proService.updatePassword(passwordDTO);
	}

	@ApiOperation("Cambiar el mail de un paciente de pros")
	@PostMapping("/updateEmail")
	public ResponseEntity updateEmail(@RequestBody final UserDTO userDto) {
		log.info("ProController.pro/updateEmail");
		return proService.updateEmail(userDto);
	}

	@ApiOperation("Recuperar pro de un paciente")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/pro")
	public List getPatientPro(@RequestBody final UserDTO userDto) throws ServiceException {
		log.info("ProController.pro/pro");
		return proService.getPatientPro(userDto);
	}

	@ApiOperation("Recuperar pros de un paciente")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/pros")
	public List getPatientPros(@RequestBody final UserDTO userDto) throws ServiceException {
		log.info("ProController.pro/pros");
		return proService.getPatientPros(userDto);
	}


}
