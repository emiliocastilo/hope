/**
 * 
 */
package es.plexus.hopes.hopesback.controller.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author jose.estevezbarroso
 *
 */
@ApiModel
@Data
public class HealthOutcomeDTO {

	@ApiModelProperty(position = 10, example = "1L", value = "Identificador en la BD")
	private Long id;
	
	@ApiModelProperty(position = 10, example = "1L", value = "Identificador de paciente en la BD")
	private Long patientId;
	
	@ApiModelProperty(position = 10, example = "NAPSI", value = "Tipo de indice")
	private String indexType;
	
	@ApiModelProperty(position = 10, example = "6", value = "Identificador en la BD")
	private BigDecimal value;
	
	@ApiModelProperty(position = 10, example = "Leve", value = "Interpretación de ese valor, según la escala del indice")
	private String result;
	
	@ApiModelProperty(position = 20, example = "1981-01-01T00:00:00Z", value = "Fecha del dato")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
	
}
