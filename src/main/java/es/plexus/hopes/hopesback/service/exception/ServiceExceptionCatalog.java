package es.plexus.hopes.hopesback.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServiceExceptionCatalog {

	UNKNOWN_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE, "IE_001", "Excepción desconocida"),
	NOT_FOUND_ELEMENT_EXCEPTION(HttpStatus.NOT_FOUND, "IE_002", "Elemento no encontrado"),
	USERNAME_DUPLICATE_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-003", "Nombre de usuario duplicado"),
	TOO_MANY_ELEMENTS_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-004", "Se encontraron demasiados elementos"),
	INVALID_LOGIN_EXCEPTION(HttpStatus.FORBIDDEN, "IE-005", "Nombre de usuario o contraseña no válidos"),
	INVALID_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-006", "La petición no es correcta."),
	VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-007", "Ya existe el valor del campo "),
	NHC_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-008", "Ya existe el valor del campo nhc"),
	HEALTH_CARD_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-009", "Ya existe el valor del campo tarjeta sanitaria"),
	DNI_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-010", "Ya existe el valor del campo dni"),
	EMAIL_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-011", "Ya existe el valor del campo email"),
	ROLE_NAME_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-012", "Ya existe el valor del campo nombre"),
	COLLEGE_NUMBER_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-013", "Ya existe el valor del campo número de colegiado"),
	PHONE_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-014", "Ya existe el valor del campo número de teléfono");

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
