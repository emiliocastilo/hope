package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@ApiModel
@Data
public class RoleDTO {

	@ApiModelProperty(position = 10, example = "1L", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "Administrador", value = "Nombre del rol")
	@NotBlank
	private String name;

	@ApiModelProperty(position = 30, example = "ROLE_ADMIN", value = "Código del rol")
	@NotBlank
	private String code;
	
	@ApiModelProperty(position = 40, example = "Rol administrador. Tiene le máximo nivel de acceso a la aplicación", value = "Descripcion del rol")
	@NotBlank
	private String description;

	@ApiModelProperty(position = 50, example = "Hospital 1", value = "Hospital asociados al Rol")
	@NotNull
	private HospitalDTO hospital;

	@ApiModelProperty(position = 60, example = "Servicio 1", value = "Servicio asociado al Rol")
	@NotNull
	private ServiceDTO service;

	@ApiModelProperty(position = 70, example = "Patología 1", value = "Patología asociada al Rol")
	@NotNull
	private PathologyDTO pathology;
}
