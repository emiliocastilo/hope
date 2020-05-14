package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HealthOutcomeDTO {
    
	@ApiModelProperty(position = 10, example = "Leve", value = "Resultado de salud")
	private String result;
	@ApiModelProperty(position = 20, example = "50", value = "Numero de causiticas")
    private Long count;
   
}
