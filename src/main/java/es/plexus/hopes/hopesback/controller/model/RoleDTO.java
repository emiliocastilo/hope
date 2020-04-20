package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.repository.model.ERole;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class RoleDTO {

	private Long id;
	private ERole name;
	private String description;

}
