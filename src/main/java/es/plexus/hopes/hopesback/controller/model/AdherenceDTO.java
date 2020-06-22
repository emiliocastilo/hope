package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel
public class AdherenceDTO {

	@ApiModelProperty(position = 10, example = "1981-01-01T00:00:00Z", value = "Fecha de la dispensación al paciente")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@NotBlank
	private LocalDateTime date;

	@ApiModelProperty(position = 20, example = "1", value = "Descripción sobre la dispensación")
	@NotBlank
	private String description;

	@ApiModelProperty(position = 30, example = "1", value = "Cantidad a tomar por el paciente/Cantidad proporcionada en la dispensación")
	@NotBlank
	private String quantity;

	@ApiModelProperty(position = 40, example = "1", value = "Precio del medicamento/dispensacion")
	private BigDecimal amount;

	@ApiModelProperty(position = 50, example = "1", value = "Días para los que se dispensa la medicación")
	@NotBlank
	private Integer daysDispensation;

}
