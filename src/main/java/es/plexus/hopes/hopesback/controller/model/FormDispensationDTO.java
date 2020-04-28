package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@ApiModel
public class FormDispensationDTO {

	@ApiModelProperty(position = 10, example = "1981-01-01T00:00:00Z", value = "Inicio del período de las dispensaciones")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@NotBlank
	private LocalDateTime startPeriod;

	@ApiModelProperty(position = 20, example = "1981-01-01T00:00:00Z", value = "Fin del período de las dispensaciones")
	@NotBlank
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime endPeriod;

	@ApiModelProperty(position = 30, example = "1", value = "Fichero de carga con las dispensaciones")
	@NotBlank
	private MultipartFile fileDispensation;
}
