package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@ApiModel
@Data
public class ServiceDTO {

	@ApiModelProperty(position = 10, example = "1L", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "Servicio Dermatología", value = "Nombre del servicio")
	@NotBlank
	private String name;

	@ApiModelProperty(position = 30, example = "['Pathology 1']", value = "Patologías asociadas al Servicio")
	private Set<PathologyDTO> pathologies;

}
