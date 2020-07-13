package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDinamycFormDTO {
	@ApiModelProperty(position = 10, example = "datepicker", value = "Tipo de campo del formulario")
	String type;

	@ApiModelProperty(position = 20, example = "date", value = "Nombre del campo en el formulario ha de coincidir con el que se ha configurado en la plantilla")
	String name;

}
