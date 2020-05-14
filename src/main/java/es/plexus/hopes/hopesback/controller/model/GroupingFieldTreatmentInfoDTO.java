package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class GroupingFieldTreatmentInfoDTO {

	@ApiModelProperty(position = 10, example = "BIOLOGICO", value = "Campo por el que se va a agrupar la consulta del tratamiento")
	private String groupingField;

	@ApiModelProperty(position = 30, example = "56", value = "Número de pacientes")
	private Long numberPatients;

}
