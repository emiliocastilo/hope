package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CieDTO {

    @ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
    private Long id;

    @ApiModelProperty(position = 20, example = "A00.0", value = "Código del diagnóstico")
    private String code;

    @ApiModelProperty(position = 20, example = "Cólera debido a Vibrio choleare 01, biotipo cholerae", value = "Descripción del diagnóstico")
    private String description;
}
