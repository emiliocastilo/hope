package es.plexus.hopes.hopesback.service.exception;

import es.plexus.hopes.hopesback.error.CustomExceptionHandler;
import org.springframework.http.HttpStatus;

public class ServiceException extends CustomExceptionHandler {

	public ServiceException(final String message, final Throwable cause, final String errorCode,
							final HttpStatus httpStatus, final String developerMessage) {
		super(message, cause, errorCode, httpStatus, developerMessage);
	}

	public ServiceException(final String message, final String errorCode, final HttpStatus httpStatus,
							final String developerMessage) {
		super(message, errorCode, httpStatus, developerMessage);
	}

	public ServiceException(final Throwable cause, final String errorCode, final HttpStatus httpStatus,
							final String developerMessage) {
		super(cause, errorCode, httpStatus, developerMessage);
	}

	public ServiceException(final String errorCode, final HttpStatus httpStatus, final String developerMessage) {
		super(errorCode, httpStatus, developerMessage);
	}
}
