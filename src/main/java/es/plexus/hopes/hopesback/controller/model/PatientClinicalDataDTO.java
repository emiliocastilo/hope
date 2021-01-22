package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel
public class PatientClinicalDataDTO {

    @ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
    private Long id;

    @ApiModelProperty(position = 20, example = "paciente 1", value = "Paciente asociado al diagnóstico")
    private PatientDTO patient;

    @ApiModelProperty(position = 50, example = "CVP", value = "Nombre de la infección ")
    private String name;

    @ApiModelProperty(position = 50, example = "100", value = "Valor del resultado")
    private String value;

    @ApiModelProperty(position = 50, example = "COLERA", value = "Descripción CIE")
    private String description;

    @ApiModelProperty(position = 50, example = "COLERA", value = "Descripción CIE")
    private String classification;

    @ApiModelProperty(position = 20, example = "1981-01-01T00:00:00Z", value = "Fecha del dato")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
}
