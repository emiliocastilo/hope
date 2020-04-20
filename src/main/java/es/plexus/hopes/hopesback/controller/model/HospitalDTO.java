package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@ApiModel
@Data
public class HospitalDTO {

	private Long id;
	private String name;
	private Set<PathologyDTO> pathologyDTOS = new HashSet<>();

}
