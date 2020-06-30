package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@ApiModel
public class FormDTO {

    @NotEmpty
    private String template;

    @NotNull
    private Integer patientId;

    @NotEmpty
    private List<InputDTO> data;

    private Date dateTime;
}
