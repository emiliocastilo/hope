package es.plexus.hopes.hopesback.controller.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DetailGraphDTO {
    
	@ApiModelProperty(position = 10, example = "MDMD610604911012", value = "Número de la tarjeta sanitaria")
	private String nhc;	 
	
	@ApiModelProperty(position = 20, example = "MDMD610604911012", value = "Codigo de la tarjeta sanitaria")
	private String sip;
	
	@ApiModelProperty(position = 30, example = "Julio Rivera Calderón", value = "Nombre y apellidos del paciente")
	private String patient;
	
	@ApiModelProperty(position = 40, example = "Psoriasis en placas", value = "Indicación del tratamiento")
	private String indication;
	
	@ApiModelProperty(position = 50, example = "Indication1", value = "Indicación del diagnostico")
	private String diagnostig;
	
	@ApiModelProperty(position = 60, example = "Aspirina", value = "Medicamento asociado al tratamiento")
	private String treatment;
	
	@ApiModelProperty(position = 70, example = "1.0", value = "Valor pasi")
	private String pasi;
	
	@ApiModelProperty(position = 80, example = "1981-01-01T00:00:00Z", value = "Fecha PASI")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime datePasi;
	
	@ApiModelProperty(position = 90, example = "1.0", value = "Valor dlqi")
	private String dlqi;
	
	@ApiModelProperty(position = 100, example = "1981-01-01T00:00:00Z", value = "Fecha DLQI")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dateDlqi;
   
}
