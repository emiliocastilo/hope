package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TreatmentInfoDTO {
    
	@ApiModelProperty(position = 10, example = "B01AC06", value = "Código de Sistema de Clasificación Anatómica")
	private String codeAct;
	@ApiModelProperty(position = 20, example = "Ácido acetilsalicílico", value = "Principio activo del medicamento")
    private String actIngredients; 
	@ApiModelProperty(position = 30, example = "50", value = "Numero de causiticas")
    private Long count;
   
}
