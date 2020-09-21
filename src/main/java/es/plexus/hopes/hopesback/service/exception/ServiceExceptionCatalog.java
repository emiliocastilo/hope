package es.plexus.hopes.hopesback.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.COLLEGE_NUMBER_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.DNI_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.EMAIL_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.HEALTH_CARD_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NHC_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_DATE_HISTORIFY_FORM_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_DATE_IN_FORM_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_GRAPHS_FORM_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_HISTORIFY_FORM_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_PATIENT_IN_FORM_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.PHONE_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.ROLE_NAME_VIOLATION_CONSTRAINT_MESSAGE;

@Getter
public enum ServiceExceptionCatalog {

	UNKNOWN_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE, "IE_001", "Excepción desconocida"),
	NOT_FOUND_ELEMENT_EXCEPTION(HttpStatus.NOT_FOUND, "IE_002", "Elemento no encontrado"),
	USERNAME_DUPLICATE_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-003", "Nombre de usuario duplicado"),
	TOO_MANY_ELEMENTS_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-004", "Se encontraron demasiados elementos"),
	INVALID_LOGIN_EXCEPTION(HttpStatus.FORBIDDEN, "IE-005", "Nombre de usuario o contraseña no válidos"),
	INVALID_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-006", "La petición no es correcta."),
	VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-007", "Ya existe el valor del campo "),
	NHC_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-008", NHC_VIOLATION_CONSTRAINT_MESSAGE),
	HEALTH_CARD_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-009", HEALTH_CARD_VIOLATION_CONSTRAINT_MESSAGE),
	DNI_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-010", DNI_VIOLATION_CONSTRAINT_MESSAGE),
	EMAIL_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-011", EMAIL_VIOLATION_CONSTRAINT_MESSAGE),
	ROLE_NAME_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-012", ROLE_NAME_VIOLATION_CONSTRAINT_MESSAGE),
	COLLEGE_NUMBER_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-013", COLLEGE_NUMBER_VIOLATION_CONSTRAINT_MESSAGE),
	PHONE_VIOLATION_CONSTRAINT_EXCEPTION(HttpStatus.BAD_REQUEST,"IE-014",PHONE_VIOLATION_CONSTRAINT_MESSAGE),
	BAD_FILE_EXTENSION_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-015", "Extensión del fichero no válido (solo XLSX o XLS)"),
	NOT_HISTORIFY_FORM_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-016", NOT_HISTORIFY_FORM_MESSAGE),
	NOT_GRAPHS_FORM_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-017", NOT_GRAPHS_FORM_MESSAGE),
	NOT_DATE_HISTORIFY_FORM_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-018", NOT_DATE_HISTORIFY_FORM_MESSAGE),
	NOT_PATIENT_IN_FORM_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-019", NOT_PATIENT_IN_FORM_MESSAGE),
	NOT_DATE_IN_FORM_EXCEPTION(HttpStatus.BAD_REQUEST, "IE-020", NOT_DATE_IN_FORM_MESSAGE);

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
