package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class InputDTO {

    private String type;
    private String name;
    private String value;
}
