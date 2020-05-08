package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class RoleDTO {

	@ApiModelProperty(position = 10, example = "1L", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "ROLE_ADMIN", value = "Nombre del rol")
	@NotBlank
	private String name;

	@ApiModelProperty(position = 30, example = "Permisos de administrador", value = "Breve descripción de las funciones del rol")
	private String description;
}
