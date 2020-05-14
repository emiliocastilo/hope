package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class IndicationInfoDTO {

	@ApiModelProperty(position = 10, example = "A00(CIE10), 140(CIE9)", value = "Nombre de la indicación")
	private String indication;

	@ApiModelProperty(position = 30, example = "56", value = "Número de pacientes por indicación")
	private Long numberPatients;

}
