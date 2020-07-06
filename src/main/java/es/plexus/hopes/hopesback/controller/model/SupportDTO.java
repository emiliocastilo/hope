package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class SupportDTO {

    @ApiModelProperty(position = 40, example = "carlos.ruiz@hotmail.com", value = "Email donde va a recibir la respuesta por parte del equipo de soporte")
    @Email
    private String email;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;
}
