package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.controller.validation.IsValidJson;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel
@Data
public class TemplateDTO {

    @NotEmpty
    private String key;

    @IsValidJson
    @NotEmpty
    private String value;
}
