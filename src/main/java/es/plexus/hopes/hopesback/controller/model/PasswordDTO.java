package es.plexus.hopes.hopesback.controller.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
