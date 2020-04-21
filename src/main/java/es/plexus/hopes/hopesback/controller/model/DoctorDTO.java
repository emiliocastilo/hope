package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class DoctorDTO {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "Francisco", value = "Nombre del médico")
	private String name;

	@ApiModelProperty(position = 30, example = "Lopez", value = "Apellido del médico")
	private String surname;

	@ApiModelProperty(position = 40, example = "123456789", value = "Teléfono del médico")
	private String phone;

	@ApiModelProperty(position = 50, example = "12345678Z", value = "Documento de identidad del médico")
	private String dni;

	@ApiModelProperty(position = 60, example = "12345678", value = "Número de colegiado médico")
	private Long collegeNumber;

	@ApiModelProperty(position = 70, example = "Francisco", value = "Nombre de usuario relacionado al médico")
	private String username;

	@ApiModelProperty(position = 80, example = "ContraseñaFrancisco", value = "Contraseña del usuario")
	private String password;

	@ApiModelProperty(position = 90, example = "francisco@gmail.com", value = "Correo del usuario")
	private String email;

	@ApiModelProperty(position = 100, example = "1", value = "Identificador del hospital al que pertenece el médico")
	private Long hospitalId;

	@ApiModelProperty(position = 110, example = "[1]", value = "Listado de roles del usuario")
	private List<Long> roleList;

	@ApiModelProperty(position = 120, example = "1", value = "Servicio relacionado al médico")
	private Long serviceId;

}
