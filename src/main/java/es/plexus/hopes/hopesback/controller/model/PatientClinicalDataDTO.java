package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
}
