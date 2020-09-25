package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphHistorifyDinamycFormDTO {
	@ApiModelProperty(position = 10, example = "Leucocitos", value = "Nombre del campo a mostrar en la gráfica")
	String name;

	@ApiModelProperty(position = 20, example = "", value = "Listado de valores")
	List<DataHistoricDinamycFormDTO> values;

}
