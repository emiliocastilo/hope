package es.plexus.hopes.hopesback.controller.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserSimpleDTO {
	
	@ApiModelProperty(position = 20, example = "carlos.ruiz", value = "Nombre del usuario")
	@NotBlank(message = "Username is mandatory")
	private String username;

	@ApiModelProperty(position = 40, example = "carlos.ruiz@hotmail.com", value = "Email relacionado al usuario")
	@NotBlank(message = "Email is mandatory")
	@Email
	private String email;

	@ApiModelProperty(position = 50, example = "[1, 2]", value = "Lista de roles que tiene el usuario")
	@NotEmpty(message = "At least one rol is mandatory")
	private Set<Long> roles;

	@ApiModelProperty(position = 60, example = "1", value = "Identificador del hospital al que pertenece el usuario")
	@NotNull(message = "Hospital is mandatory")
	private Long hospitalId;
}
