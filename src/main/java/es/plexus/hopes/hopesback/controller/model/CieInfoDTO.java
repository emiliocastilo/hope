package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CieInfoDTO{

	@ApiModelProperty(position = 10, example = "A00(CIE10), 140(CIE9)", value = "Código de la indicación por CIE correspondiente")
	private String code;

	@ApiModelProperty(position = 20, example = "Enfermedades infecciosas intestinales", value = "Descripción de la indicación por el CIE correspondiente")
	private String description;

	@ApiModelProperty(position = 30, example = "56", value = "Número de pacientes por indicación")
	private Long numberPatients;

}
