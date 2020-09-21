package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class IndicationDTO {

    @ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
    private Long id;

    @ApiModelProperty(position = 20, example = "EN PLACAS", value = "Descripción")
    private String description;

    @ApiModelProperty(position = 30, example = "1", value = "Identificador de la patología")
    private Long pathologyId;
}
