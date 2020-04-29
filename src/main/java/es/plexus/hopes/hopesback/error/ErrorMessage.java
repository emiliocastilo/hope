package es.plexus.hopes.hopesback.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorMessage {

	private HttpStatus httpStatus;
	private String errorCode;
	private String message;
	private String developerMessage;

}
