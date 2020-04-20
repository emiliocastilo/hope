package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class ServiceDTO {

	private Long id;
	private String name;
	private String description;
	private Long doctorId;

}
