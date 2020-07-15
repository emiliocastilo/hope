package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataHistoricDinamycFormDTO {
	@ApiModelProperty(position = 10, example = "2020-06-01T00:00:00.000Z", value = "Fecha del dato historificado")
	String date;

	@ApiModelProperty(position = 20, example = "23", value = "Valor del dato historificado")
	String value;

}
