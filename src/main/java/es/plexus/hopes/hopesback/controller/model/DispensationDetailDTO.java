package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel
public class DispensationDetailDTO {
	@ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
	@NotBlank
	private Long id;

	@ApiModelProperty(position = 20, example = "1981-01-01T00:00:00Z", value = "Fecha de la dispensación al paciente")
	@NotBlank
	private LocalDateTime date;

	@ApiModelProperty(position = 30, example = "MDMD610604911012", value = "El NHC es el identificador utilizado para marcar las historias clínicas hospitalarias. Es un número secuencial que proporciona el ordenador en el momento en el que el paciente acude por primera vez al centro sanitario a recibir asistencial.")
	@Pattern(regexp = "[0-9][A-Za-z]")
	private String nhc;

	@ApiModelProperty(position = 40, example = "1", value = "Código del medicamento")
	@Pattern(regexp = "^[A-Za-z]{2}[0-9]$")
	private String code;

	@ApiModelProperty(position = 50, example = "1", value = "Código del medicamento segun la AEMPS")
	@Pattern(regexp = "^[0-9]{6}$")
	private Integer nationalCode;

	@ApiModelProperty(position = 60, example = "1", value = "Descripción sobre la dispensación")
	@NotBlank
	private String description;

	@ApiModelProperty(position = 70, example = "1", value = "Cantidad a tomar por el paciente/Cantidad proporcionada en la dispensación")
	@NotBlank
	private String quantity;

	@ApiModelProperty(position = 80, example = "1", value = "Precio del medicamento/dispensacion")
	private BigDecimal amount;

	@ApiModelProperty(position = 90, example = "1", value = "Días para los que se dispensa la medicación")
	@NotBlank
	private Integer daysDispensation;

	@ApiModelProperty(position = 100, example = "1", value = "Referencia a la carga de dispensación")
	private DispensationDTO dispensation;
}
