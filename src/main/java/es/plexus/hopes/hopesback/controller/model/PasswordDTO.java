package es.plexus.hopes.hopesback.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PasswordDTO {
	
	@NotBlank
    @Size(max = 200)
    private String password;

	private  String token;
	
	@NotBlank
    @Size(max = 200)
    private String newPassword;
	
}
