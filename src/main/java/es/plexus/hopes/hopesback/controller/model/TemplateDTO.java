package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.controller.validation.IsValidJson;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel
@Data
public class TemplateDTO {

    @NotEmpty
    private String key;

    @IsValidJson
    @NotEmpty
    private String form;

    @NotEmpty
    private String buttons;

    private Boolean historify;

    private String nameHistoricalDate;

    private List<Object> fieldsToGraph;
}
