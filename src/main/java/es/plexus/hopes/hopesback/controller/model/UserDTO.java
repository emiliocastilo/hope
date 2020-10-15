package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Set;

@ApiModel
@Data
public class UserDTO {

	@ApiModelProperty(position = 10, example = "1L", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "carlos.ruiz", value = "Nombre del usuario")
	private String username;

	@ApiModelProperty(position = 40, example = "carlos.ruiz@hotmail.com", value = "Email relacionado al usuario")
	@Email
	private String email;

	@ApiModelProperty(position = 50, example = "[1, 2]", value = "Lista de roles que tiene el usuario")
	private Set<Long> roles;

	@ApiModelProperty(position = 70, example = "carlos", value = "Nombre del usuario")
	private String name;

	@ApiModelProperty(position = 80, example = "Ruíz", value = "Apellido del usuario")
	private String surname;

	@ApiModelProperty(position = 90, example = "600000000", value = "Teléfono del usuario")
	@Pattern(regexp = "^[0-9]{2,3}-? ?[0-9]{6,7}$")
	private String phone;

	@ApiModelProperty(position = 100, example = "52201960X", value = "DNI del usuario")
	@Pattern(regexp = "^[0-9]{8}[A-Za-z]$")
	private String dni;

	@ApiModelProperty(position = 110, example = "075566666", value = "Número de colegiado del médico")
	private Long collegeNumber;
}
