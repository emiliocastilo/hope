package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@ApiModel
public class UserUpdateDTO {

	@ApiModelProperty(position = 10, example = "1L", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "carlos.ruiz", value = "Nombre del usuario")
	private String username;

	@ApiModelProperty(position = 40, example = "carlos.ruiz@hotmail.com", value = "Email relacionado al usuario")
	@Email
	private String email;

	@ApiModelProperty(position = 50, example = "1", value = "Identificador del hospital al que pertenece el usuario")
	private Long hospitalId;

}