package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@ApiModel
@Data
public class HospitalDTO {

	@ApiModelProperty(position = 10, example = "1L", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "Hospital Universitario Rey Juan Carlos", value = "Nombre del hospital")
	@NotBlank
	private String name;

	@ApiModelProperty(position = 30, example = "['Servicio 1']", value = "Servicios asociados al Hospital")
	private Set<ServiceDTO> services;

	@ApiModelProperty(position = 40, example = "['Pathology 1']", value = "Patologías asociadas al Hospital")
	private Set<PathologyDTO> pathologies;
}
