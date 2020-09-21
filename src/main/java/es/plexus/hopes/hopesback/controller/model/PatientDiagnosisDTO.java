package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel
public class PatientDiagnosisDTO {

    @ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
    private Long id;

    @ApiModelProperty(position = 20, example = "paciente 1", value = "Paciente asociado al diagnóstico")
    private PatientDTO patient;

    @ApiModelProperty(position = 30, example = "indicación 1", value = "Indicación asociada al diagnóstico")
    private IndicationDTO indication;

    @ApiModelProperty(position = 40, example = "001", value = "Código CIE")
    private String cieCode;

    @ApiModelProperty(position = 50, example = "COLERA", value = "Descripción CIE")
    private String cieDescription;

    @ApiModelProperty(position = 60, example = "otras indicaciones", value = "Otras indicaciones")
    private String othersIndications;

    @ApiModelProperty(position = 70, example = "1981-01-01T00:00:00Z", value = "Fecha de inicio del diagnóstico")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime initDate;

    @ApiModelProperty(position = 80, example = "1981-01-01T00:00:00Z", value = "Fecha de los síntomas")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime symptomsDate;

    @ApiModelProperty(position = 90, example = "1981-01-01T00:00:00Z", value = "Fecha de derivación")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime derivationDate;

    @ApiModelProperty(position = 100, example = "[tratamiento 1]", value = "Tratamientos asociados al diagnóstico")
    private List<PatientTreatmentDTO> treatments;
}
