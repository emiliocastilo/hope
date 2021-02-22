package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel
public class SuspendTreatmentDTO {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador de la linea en la BD.")
	private Long lineId;

	@ApiModelProperty(position = 20, example = "1", value = "Razón de la suspensión.")
	private String reason;

	@ApiModelProperty(position = 30, example = "1", value = "Fecha de suspensión.")
	private LocalDateTime suspensionDate;
}
