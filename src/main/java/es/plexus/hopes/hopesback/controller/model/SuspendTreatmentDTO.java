package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SuspendTreatmentDTO {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador de tratamiento en la BD.")
	private Long treatmentId;

	@ApiModelProperty(position = 10, example = "1", value = "Razón de la suspensión.")
	private String reason;
	//TODO Falta la fecha suspensión
}
