package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GraphHealthOutcomeDTO {

	@ApiModelProperty(position = 10, example = "DLQI", value = "Índice clínico")
	private String indexType;

	@ApiModelProperty(position = 20, example = "36.54", value = "Valor")
	private BigDecimal value;

	@ApiModelProperty(position = 30, example = "1981/02/02T00:00:00Z", value = "Fecha del valor")
	private LocalDateTime date;

}
