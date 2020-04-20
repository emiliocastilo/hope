package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@ApiModel
@Data
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String email;
	private LocalDate dateCreation;
	private LocalDate dateModification;
	private Long hospitalId;
	private List<Long> roleList;
	private boolean active;

}
