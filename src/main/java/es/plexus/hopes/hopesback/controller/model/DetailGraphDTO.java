package es.plexus.hopes.hopesback.controller.model;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DetailGraphDTO {
    
	@ApiModelProperty(position = 10, example = "MDMD610604911012", value = "Codigo de la tarjeta sanitaria")
	private String nhc;	 
	
	private String sip;
	
	private String patient;
	
	private String indication;
	
	private String diagnostig;
	
	private String treatment;
	
	private String pasi;
	
	private LocalDateTime datePasi;
	
	private String dlqi;
	
	private LocalDateTime dateDlqi;
   
}
