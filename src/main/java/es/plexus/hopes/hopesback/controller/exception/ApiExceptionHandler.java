package es.plexus.hopes.hopesback.controller.exception;

import es.plexus.hopes.hopesback.error.CustomExceptionHandler;
import es.plexus.hopes.hopesback.error.ErrorMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ApiExceptionHandler {
	private final ModelMapper modelMapper;

	@Autowired
	public ApiExceptionHandler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@ExceptionHandler(value = CustomExceptionHandler.class)
	public ResponseEntity<Object> handlerApiCustomExceptionHandler(final CustomExceptionHandler customExceptionHandler) {
		final ErrorMessage errorMessage = modelMapper.map(customExceptionHandler, ErrorMessage.class);

		return new ResponseEntity<>(errorMessage, errorMessage.getHttpStatus());
	}

	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<Object> handlerThrowable(final Throwable throwable) {
		final ErrorMessage errorMessage = modelMapper.map(ApiErrorCatalog.GENERIC_ERROR, ErrorMessage.class);

		final String developerMessage = throwable.getCause() == null ? throwable.getMessage() :
				throwable.getCause().getMessage();
		errorMessage.setDeveloperMessage(developerMessage);

		//TODO METER LOGS

		return new ResponseEntity<>(errorMessage, errorMessage.getHttpStatus());
	}
}
