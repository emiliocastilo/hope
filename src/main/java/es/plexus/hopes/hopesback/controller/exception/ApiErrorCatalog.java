package es.plexus.hopes.hopesback.controller.exception;

import es.plexus.hopes.hopesback.service.exception.ServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiErrorCatalog {

	GENERIC_ERROR("API-001", "Ocurrió un error inexperado.", HttpStatus.SERVICE_UNAVAILABLE);

	private final String errorCode;
	private final String message;
	private final HttpStatus httpStatus;

	ApiErrorCatalog(String errorCode, String message, HttpStatus httpStatus) {
		this.errorCode = errorCode;
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public ServiceException exception() {
		return new ServiceException(this.errorCode, this.httpStatus, this.message);
	}

	public ServiceException exception(final String developerMessage) {
		return new ServiceException(this.message, this.errorCode, this.httpStatus, developerMessage);
	}

	public ServiceException exception(final Throwable throwable) {
		return new ServiceException(throwable, this.errorCode, this.httpStatus, this.message);
	}

	public ServiceException exception(final Throwable throwable, final String developerMessage) {
		return new ServiceException(developerMessage, throwable, this.errorCode, this.httpStatus, this.message);
	}
}
