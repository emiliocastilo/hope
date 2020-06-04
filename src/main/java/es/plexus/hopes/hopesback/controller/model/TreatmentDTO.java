package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TreatmentDTO {

    @ApiModelProperty(position = 20,
            example = "true",
            value = "Indicador que indica si está o no activo el tratamiento")
    private boolean active;

    @ApiModelProperty(position = 30,
            example = "Biologico",
            value = "Tipo del medicamento")
    private String type;

    @ApiModelProperty(position = 40,
            example = "Paracetamol",
            value = "Medicamento del tratamiento")
    private MedicineDTO medicine;

    @ApiModelProperty(position = 50,
            example = "3 mg",
            value = "Dosis del tratamiento")
    private String dose;

    @ApiModelProperty(position = 60,
            example = "1981-01-01T00:00:00Z",
            value = "Fecha inicial del tratamiento")
    private LocalDateTime initDate;

    @ApiModelProperty(position = 70,
            example = "1981-01-01T00:00:00Z",
            value = "Fecha final del tratamiento")
    private LocalDateTime finalDate;

    @ApiModelProperty(position = 70,
            example = "Object",
            value = "Adherencia al tratamiento del paciente. Listado de veces que ha recogido la medicación")
    List<AdherenceDTO> adherence;

}
