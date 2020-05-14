package es.plexus.hopes.hopesback.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthOutcomeDTO {
    
	private String result;
    private Long count;
   
}
