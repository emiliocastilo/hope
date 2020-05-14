package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PatientDosesInfoDTO {
    
	@ApiModelProperty(position = 10, example = "Desinfectada", value = "Regimen del tratamientodel paciente")
	private String regiment;	 
	@ApiModelProperty(position = 20, example = "50", value = "Numero de causiticas")
    private Long count;
   
}
