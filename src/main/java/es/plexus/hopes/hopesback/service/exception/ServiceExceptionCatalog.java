package es.plexus.hopes.hopesback.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServiceExceptionCatalog {

	UNKNOWN_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE, "IE_001", "Unknown exception"),
	NOT_FOUND_ELEMENT_EXCEPTION(HttpStatus.NOT_FOUND, "IE_002", "Not Found element"),
	USERNAME_DUPLICATE_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-003", "Username duplicate"),
	TOO_MANY_ELEMENTS_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-004", "Too many elements found"),
	INVALID_LOGIN(HttpStatus.FORBIDDEN, "IE-005", "Invalid username or password");

	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String message;

	ServiceExceptionCatalog(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
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
