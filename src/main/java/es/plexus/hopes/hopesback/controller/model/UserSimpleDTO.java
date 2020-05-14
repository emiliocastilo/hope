package es.plexus.hopes.hopesback.controller.model;

import java.util.Set;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class UserSimpleDTO {
	
	private String username;
	private String email;
	private Set<Long> roles;
	private Long hospitalId;	
	private RoleDTO rolSelected;
}
