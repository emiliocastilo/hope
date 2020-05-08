package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel
public class FormDTO {

    @NotEmpty
    private String template;

    private Integer patientId;

    @NotEmpty
    private List<InputDTO> data;
}
