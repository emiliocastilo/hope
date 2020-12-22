package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class FormCardiovascularRiskDTO {

    @ApiModelProperty(position = 10, example = "30", value = "Edad del paciente", required = true)
    private int age;

    @ApiModelProperty(position = 20, example = "Mujer", value = "Genero del paciente", required = true)
    private String gender;

    @ApiModelProperty(position = 30, example = "true", value = "Si es diabético el paciente", required = true)
    private boolean diabetic;

    @ApiModelProperty(position = 40, example = "true", value = "Si es fumador el paciente", required = true)
    private boolean smoker;

    @ApiModelProperty(position = 50, example = "60", value = "Tensión arterial sistólica, debe ser igual o superior a 60", required = true)
    private int pas;

    @ApiModelProperty(position = 60, example = "30", value = "Tensión arterial diastólica, debe ser igual o superior a 30", required = true)
    private int pad;

    @ApiModelProperty(position = 70, example = "100", value = "Colesterol total, debe ser igual o superior a 100", required = true)
    private int cholesterolTotal;

    @ApiModelProperty(position = 80, example = "20", value = "Colesterol hdl, debe ser igual o superior a 20")
    private int hdl;
}
