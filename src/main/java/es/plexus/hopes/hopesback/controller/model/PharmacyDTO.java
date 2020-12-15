package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel
public class PharmacyDTO {

    @ApiModelProperty(position = 10, example = "1", value = "NHC del paciente")
    private String nhc;

    @ApiModelProperty(position = 20, example = "1981-01-01T00:00:00Z", value = "Fecha de carga de las dispensaciones")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @ApiModelProperty(position = 30, example = "711415P", value = "Código nacional del medicamento")
    @Pattern(regexp = "^[0-9]{6}[A-Za-z]{7}$")
    private String nationalCode;

    @ApiModelProperty(position = 50, example = "ABACAVIR/LAMIVUDINA DR. REDDYS 600MG/300MG COMPRIMIDOS RECUBIERTOS CON PELICULA EFG 30 comprimidos", value = "Presentación del medicamento")
    private String presentation;

    @ApiModelProperty(position = 60, example = "1", value = "Cantidad a tomar por el paciente/Cantidad proporcionada en la dispensación")
    private String quantity;

    @ApiModelProperty(position = 70, example = "50", value = "MG dispensados")
    private BigDecimal mgDispensed;

    @ApiModelProperty(position = 80, example = "5", value = "PMF unitario")
    private BigDecimal unitCost;

    @ApiModelProperty(position = 90, example = "50", value = "PMF total")
    private BigDecimal totalCost;

    @ApiModelProperty(position = 100, example = "true", value = "Ensayo clínico")
    private boolean testClinical;

    public PharmacyDTO(String nhc, LocalDateTime date, String nationalCode,
                       String presentation, String quantity, BigDecimal amount) {
        // Fixme indican el ID Paciente pero entendemos que es el NHC del paciente
        this.nhc = nhc;
        this.date = date;
        this.nationalCode = nationalCode;
        this.presentation = presentation;
        // Fixme no sabemos si es necesario este campo o se unificará al mgDispensed
        this.quantity = "";
        // Fixme la quantity de la tabla dispensation_details supuestamente debe venir un número siempre y serán los MG
        this.mgDispensed = new BigDecimal(quantity);
        // Fixme está por determinar cómo nos vendrán los datos de dispensaciones, por ahora se pone que el amount de la tabla será el coste unitario
        this.unitCost = amount;
        this.totalCost = BigDecimal.ZERO;
        // Fixme no está indicando cómo saber este dato o si realmente lo necesitan
        this.testClinical = false;
    }

    public PharmacyDTO() {
    }
}
