package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.repository.model.Medicine;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel
public class PatientTreatmentLineDTO {

    @ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
    private Long id;

    @ApiModelProperty(position = 20, example = "1", value = "Identificador en la BD")
    private Long patientTreatment;

    @ApiModelProperty(position = 30, example = "1", value = "Identificador en la BD")
    private Long modificationCount;

    @ApiModelProperty(position = 40, example = "1", value = "Identificador en la BD")
    private String type;

    @ApiModelProperty(position = 50, example = "1", value = "Identificador en la BD")
    private Medicine medicine;

    @ApiModelProperty(position = 60, example = "1", value = "Identificador en la BD")
    private String dose;

    @ApiModelProperty(position = 70, example = "1", value = "Identificador en la BD")
    private String masterFormula;

    @ApiModelProperty(position = 80, example = "1", value = "Identificador en la BD")
    private String masterFormulaDose;

    @ApiModelProperty(position = 90, example = "1", value = "Identificador en la BD")
    private String regimen;

    @ApiModelProperty(position = 100, example = "1", value = "Identificador en la BD")
    private String reason;

    @ApiModelProperty(position = 110, example = "1", value = "Identificador en la BD")
    private Boolean hadMedicineChange;

    @ApiModelProperty(position = 120, example = "1", value = "Identificador en la BD")
    private Boolean active;

    @ApiModelProperty(position = 130, example = "1981-01-01T00:00:00Z", value = "Fecha de inicio del tratamiento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime suspensionDate;

    @ApiModelProperty(position = 130, example = "1", value = "Identificador en la BD")
    private Boolean deleted;

    @ApiModelProperty(position = 100, example = "1981-01-01T00:00:00Z", value = "Fecha de inicio del tratamiento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime deletionDate;


}
