package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphPatientDetailDTO {
	@ApiModelProperty(position = 10, example = "1L", value = "Id del paciente guardado en bbdd")
	Long id;

	@ApiModelProperty(position = 20, example = "NHC001", value = "Número de historia clínica del paciente en el hospital")
	String nhc;

	@ApiModelProperty(position = 30, example = "HC001", value = "Tarjeta sanitaria del paciente")
	String healthCard;

	@ApiModelProperty(position = 40, example = "Andrés García Rodaballo", value = "Nombre completo del paciente")
	String fullName;

	@ApiModelProperty(position = 50, example = "Psoriasis en placas", value = "Indicación principal del paciente")
	String principalIndication;

	@ApiModelProperty(position = 60, example = "696.1 OTRA PSORIASIS", value = "Diagnóstico principal del paciente: CIE9code + spc +CIE9desc")
	String principalDiagnose;

	@ApiModelProperty(position = 70, example = "696.1 OTRA PSORIASIS", value = "Diagnóstico principal del paciente: CIE10code + spc +CIE10desc")
	String principalDiagnoseCie10;

	@ApiModelProperty(position = 80, example = "Apremilast", value = "Nombre de las medicaciones")
	String treatment;

	@ApiModelProperty(position = 90, example = "2", value = "Resultado de la PASI")
	String pasi;

	@ApiModelProperty(position = 100, example = "1981/02/02T00:00:00Z", value = "Fecha de la última PASI")
	LocalDateTime pasiDate;

	@ApiModelProperty(position = 110, example = "Leve", value = "Resultado de la última DLQI")
	String dlqi;

	@ApiModelProperty(position = 120, example = "1981/02/02T00:00:00Z", value = "Fecha de la última DLQI")
	LocalDateTime dlqiDate;

}
