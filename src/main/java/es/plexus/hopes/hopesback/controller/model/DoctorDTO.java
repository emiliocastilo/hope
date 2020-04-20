package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel
public class DoctorDTO {

	@ApiModelProperty(position = 10, example = "1L", value = "Identificador en la BD")
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

	@ApiModelProperty(position = 70, example = "true", value = "¿Está en activo?")
	private Boolean active;

	@ApiModelProperty(position = 80, example = "2020-02-20", value = "Fecha de creación")
	private LocalDate dateCreate;

	@ApiModelProperty(position = 90, example = "2020-02-20", value = "Fecha de modificación")
	private LocalDate dateModify;

	@ApiModelProperty(position = 100, example = "1L", value = "Usuario relacionado al médico")
	private UserDTO user;

	@ApiModelProperty(position = 110, example = "1L", value = "Servicio relacionado al médico")
	private Long serviceId;

}
