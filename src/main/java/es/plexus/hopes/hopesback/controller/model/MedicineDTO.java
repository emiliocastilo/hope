package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.repository.model.AbstractAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ApiModel
public class MedicineDTO extends AbstractAudit {
    @ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
    private Long id;

    @ApiModelProperty(position = 20, example = "ABACAVIR, LAMIVUDINA", value = "Principios activos del medicamento")
    @NotNull
    private String actIngredients;

    @ApiModelProperty(position = 30, example = "J05A", value = "Código ATC del medicamento")
    @NotNull
    private String codeAct;

    @ApiModelProperty(position = 40, example = "AAS", value = "Acrónimo por el que es conocido el medicamento")
    @Pattern(regexp = "^[A-Z]$")
    private String acronym;

    @ApiModelProperty(position = 50, example = "711415P", value = "Código nacional del medicamento")
    @Pattern(regexp = "^[0-9]{6}[A-Za-z]{7}$")
    private String nationalCode;

    @ApiModelProperty(position = 60, example = "Medicamento con propiedades mágicas", value = "Descripción del medicamento")
    private String description;

    @ApiModelProperty(position = 70, example = "ABACAVIR/LAMIVUDINA DR. REDDYS 600MG/300MG COMPRIMIDOS RECUBIERTOS CON PELICULA EFG 30 comprimidos", value = "Presentación del medicamento")
    private String presentation;

    @ApiModelProperty(position = 80, example = "1 comprimido", value = "Contenido del medicamento")
    private String content;

    @ApiModelProperty(position = 90, example = "1981-01-01", value = "Fecha de autorización")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate authorizationDate;

    @ApiModelProperty(position = 100, example = "true", value = "Autorizado")
    private boolean authorized;

    @ApiModelProperty(position = 110, example = "1981-01-01", value = "Fecha fin de autorización")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDateAuthorization;

    @ApiModelProperty(position = 120, example = "false", value = "Indicación de si se comercializa el medicamento")
    private boolean commercialization;

    @ApiModelProperty(position = 130, example = "1981-01-01", value = "Fecha de comercialización")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate commercializationDate;

    @ApiModelProperty(position = 140, example = "1981-01-01", value = "Fecha fin de comercialización")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDateCommercialization;

    @ApiModelProperty(position = 150, example = "50", value = "Unidades")
    private BigDecimal units;

    @ApiModelProperty(position = 160, example = "50", value = "PVL")
    private BigDecimal pvl;

    @ApiModelProperty(position = 170, example = "50", value = "PVL unitario")
    private BigDecimal pvlUnitary;

    @ApiModelProperty(position = 180, example = "50", value = "PVP")
    private BigDecimal pvp;

    @ApiModelProperty(position = 190, example = "DERMATOLOGÍA", value = "Nombre patología")
    private String pathology;

    @ApiModelProperty(position = 200, example = "false", value = "Indicación de si el medicamento es biológico")
    private boolean biologic;

    @ApiModelProperty(position = 210, example = "oral", value = "Vía de administración")
    private String viaAdministration;

    @ApiModelProperty(position = 220, example = "Biologico", value = "Familia/Tipo del medicamento")
    private String family;

    @ApiModelProperty(position = 230, example = "anti-integrina_CTLA4", value = "Subfamilia")
    private String subfamily;

    @ApiModelProperty(position = 240, example = "Iniston", value = "Marca del medicamento")
    private String brand;
}
