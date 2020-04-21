package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class RoleDTO {

	private Long id;
	private String name;
	private String description;

}
