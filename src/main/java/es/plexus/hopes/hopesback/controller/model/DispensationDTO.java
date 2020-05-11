package es.plexus.hopes.hopesback.controller.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class DispensationDTO {
	@ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "01-01-1981 00:00:00", value = "Fecha de carga de las dispensaciones")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime date;

	@ApiModelProperty(position = 30, example = "01-01-1981 00:00:00", value = "Inicio del período de las dispensaciones")
	@NotBlank
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime startPeriod;

	@ApiModelProperty(position = 40, example = "01-01-1981 00:00:00", value = "Fin del período de las dispensaciones")
	@NotBlank
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime endPeriod;

	@ApiModelProperty(position = 50, example = "50", value = "Número de registros")
	private int numRecords;
}
