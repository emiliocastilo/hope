package es.plexus.hopes.hopesback.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class CustomExceptionHandler extends Exception {

	private final String errorCode;
	private final HttpStatus httpStatus;
	private final String developerMessage;

	public CustomExceptionHandler(final String message, final Throwable cause, final String errorCode,
								  final HttpStatus httpStatus, final String developerMessage) {
		super(message, cause);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		this.developerMessage = developerMessage;
	}

	public CustomExceptionHandler(final String message, final String errorCode, final HttpStatus httpStatus,
								  final String developerMessage) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		this.developerMessage = developerMessage;
	}

	public CustomExceptionHandler(final Throwable cause, final String errorCode, final HttpStatus httpStatus,
								  final String developerMessage) {
		super(cause);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		this.developerMessage = developerMessage;
	}

	public CustomExceptionHandler(final String errorCode, final HttpStatus httpStatus, final String developerMessage) {
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		this.developerMessage = developerMessage;
	}

	@Override
	public String toString() {
		return "CustomExceptionHandler{" +
				"errorCode='" + errorCode + '\'' +
				", httpStatus=" + httpStatus +
				", developerMessage='" + developerMessage + '\'' +
				'}';
	}
}
