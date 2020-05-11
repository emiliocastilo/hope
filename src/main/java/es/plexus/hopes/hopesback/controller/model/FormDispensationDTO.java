package es.plexus.hopes.hopesback.controller.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class FormDispensationDTO {

	
	@ApiModelProperty(position = 10, example = "01-01-1981 00:00:00", value = "Inicio del período de las dispensaciones")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@NotBlank
	private LocalDateTime startPeriod;

	@ApiModelProperty(position = 20, example = "01-01-1981 00:00:00", value = "Fin del período de las dispensaciones")
	@NotBlank
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime endPeriod;

	@ApiModelProperty(position = 30, example = "1", value = "Fichero de carga con las dispensaciones")
	@NotBlank
	private MultipartFile fileDispensation;
}
