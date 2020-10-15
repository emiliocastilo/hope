package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Set;

@ApiModel
@Data
public class UserSimpleDTO {
	
	private String username;
	private String email;
	private Set<Long> roles;
	private RoleDTO rolSelected;
}
