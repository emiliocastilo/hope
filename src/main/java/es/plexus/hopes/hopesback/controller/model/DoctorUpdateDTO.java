package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@ApiModel
public class DoctorUpdateDTO {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "Francisco", value = "Nombre del médico")
	private String name;

	@ApiModelProperty(position = 30, example = "Lopez", value = "Apellido del médico")
	private String surname;

	@ApiModelProperty(position = 40, example = "123456789", value = "Teléfono del médico")
	@Pattern(regexp = "^[0-9]{2,3}-? ?[0-9]{6,7}$")
	private String phone;

	@ApiModelProperty(position = 50, example = "12345678Z", value = "Documento de identidad del médico")
	@Pattern(regexp = "^[0-9]{8}[A-Za-z]$")
	private String dni;

	@ApiModelProperty(position = 60, example = "12345678", value = "Número de colegiado médico")
	private Long collegeNumber;

	@ApiModelProperty(position = 60, value = "Usuario relacionado al médico")
	private UserUpdateDTO userDTO;

	@ApiModelProperty(position = 70, example = "1", value = "Servicio relacionado al médico")
	private ServiceDTO serviceDTO;

}
