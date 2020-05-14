package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ApiModel
public class PatientTreatmentDTO {
    @ApiModelProperty(position = 10, example = "1L", value = "Identificador de la BBDD")
    private Long id;

    @ApiModelProperty(position = 20, value = "Paciente con el tratamiento")
    @NotNull
    private PatientDTO patient;

    @ApiModelProperty(position = 30, example = "true", value = "Indicador de si está activo o no el tratamiento")
    @NotNull
    private boolean active;

    @ApiModelProperty(position = 40, example = "Psoriasis en placas", value = "Indicación del paciente tratado")
    private String indication;

    @ApiModelProperty(position = 50, example = "Biológico", value = "Tipo de tratamiento")
    private String type;

    @ApiModelProperty(position = 60, example = "MedicineDTO", value = "Medicamento utilizado en el tratamiento")
    private MedicineDTO medicine;

    @ApiModelProperty(position = 70, example = "2mg por dia", value = "Dosis de tratamiento")
    private String dose;

    @ApiModelProperty(position = 80, value = "Formula del tratamiento ")
    private String masterFormula;

    @ApiModelProperty(position = 90, value = "Formula para el cálculo de la dosis del tratamiento")
    private String masterFormulaDose;

    @ApiModelProperty(position = 100, example = "Esquema intensificado", value = "Regimen del tratamiento actual del paciente")
    private String regimen;

    @ApiModelProperty(position = 110, example = "01-01-2020 00:00:00", value = "Fecha de inicio")
    private LocalDateTime initDate;

    @ApiModelProperty(position = 120, example = "01-01-2020 00:00:00", value = "Fecha de fin")
    private LocalDateTime finalDate;

    @ApiModelProperty(position = 130, example = "SUSPENSION", value = "Causa del fin del tratamiento")
    private String endCause;

    @ApiModelProperty(position = 140, example = "Fallecimiento", value = "Razon del fin del tratamiento")
    private String reason;

    @ApiModelProperty(position = 150, example = "50L", value = "Número de pacientes del tratamiento")
    private Long numberPatients;

}
