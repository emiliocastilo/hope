package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.repository.model.AbstractAudit;
import es.plexus.hopes.hopesback.repository.model.Recommendation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel
public class MedicineDTO extends AbstractAudit {
    @ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
    private Long id;

    @ApiModelProperty(position = 20, example = "ABACAVIR, LAMIVUDINA", value = "Principios activos del medicamento")
    @NotNull
    private String actIngredients;

    @ApiModelProperty(position = 30, example = "J05A", value = "Principios activos del medicamento")
    @NotNull
    private String codeAct;

    @ApiModelProperty(position = 40, example = "true", value = "Indicación de si está recomendado el medicamento")
    private boolean recommended;

    @ApiModelProperty(position = 50, example = "13", value = "Tipo de recomendación")
    private Recommendation recommendation;

    @ApiModelProperty(position = 60, example = "AAS", value = "Acrónimo por el que es conocido el medicamento")
    @Pattern(regexp = "^[A-Z]$")
    private String acronym;

    @ApiModelProperty(position = 70, example = "711415P", value = "Código nacional del medicamento")
    @Pattern(regexp = "^[0-9]{6}[A-Za-z]{7}$")
    private String nationalCode;

    @ApiModelProperty(position = 80, example = "Medicamento con propiedades mágicas", value = "Descripción del medicamento")
    private String description;

    @ApiModelProperty(position = 90, example = "ABACAVIR/LAMIVUDINA DR. REDDYS 600MG/300MG COMPRIMIDOS RECUBIERTOS CON PELICULA EFG 30 comprimidos", value = "Presentación del medicamento")
    private String presentation;

    @ApiModelProperty(position = 100, example = "false", value = "Indicación de si se comercializa el medicamento")
    private boolean commercialization;

}
