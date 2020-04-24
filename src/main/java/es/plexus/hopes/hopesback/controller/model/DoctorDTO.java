package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel
public class DoctorDTO {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "Francisco", value = "Nombre del médico")
	@NotBlank(message = "Name is mandatory")
	private String name;

	@ApiModelProperty(position = 30, example = "Lopez", value = "Apellido del médico")
	@NotBlank(message = "Surname is mandatory")
	private String surname;

	@ApiModelProperty(position = 40, example = "123456789", value = "Teléfono del médico")
	@NotBlank(message = "Phone is mandatory")
	@Pattern(regexp = "^[0-9]{2,3}-? ?[0-9]{6,7}$")
	private String phone;

	@ApiModelProperty(position = 50, example = "12345678Z", value = "Documento de identidad del médico")
	@NotBlank(message = "DNI is mandatory")
	@Pattern(regexp = "^[0-9]{8}[A-Za-z]$")
	private String dni;

	@ApiModelProperty(position = 60, example = "12345678", value = "Número de colegiado médico")
	@NotNull(message = "College number is mandatory")
	private Long collegeNumber;

	@ApiModelProperty(position = 60, value = "Usuario relacionado al médico")
	@NotNull(message = "User is mandatory")
	private UserDTO user;

	@ApiModelProperty(position = 70, example = "1", value = "Servicio relacionado al médico")
	@NotNull(message = "Service is mandatory")
	private ServiceDTO service;
}
