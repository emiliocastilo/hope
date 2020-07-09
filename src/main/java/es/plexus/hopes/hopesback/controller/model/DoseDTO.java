package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class DoseDTO {

    @ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
    private Long id;

    @ApiModelProperty(position = 20, example = "J05A", value = "Código ATC del medicamento")
    private String codeAtc;

    @ApiModelProperty(position = 30, example = "omeprazol", value = "Descripción de la dosis")
    private String description;

    @ApiModelProperty(position = 40, example = "500 mg/ día", value = "Dosis")
    private String doseIndicated;

    @ApiModelProperty(position = 50, example = "Estándar", value = "Régimen")
    private String recommendation;
}
