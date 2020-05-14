package es.plexus.hopes.hopesback.controller.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DetailGraphDTO {
    
	@ApiModelProperty(position = 10, example = "MDMD610604911012", value = "Codigo de la tarjeta sanitaria")
	private String nhc;	 
	
	private String dni;
	
	private String patient;
	
	private String indication;
	
	private String diagnostig;
	
	private String treatment;
	
	private String pasi;
	
	private Date datePasi;
	
	private String dlqui;
	
	private Date dateDlqui;
   
}
