package es.plexus.hopes.hopesback.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreatmentInfoDTO {
    
	private String codeAct;
    private String actIngredients;    
    private Long count;
   
}
