package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PatientDashboardDetailDTO {
	@ApiModelProperty(position = 10,
			example = "{'DLQI':['date':'1981-01-01T00:00:00Z', 'value':'2.5'], 'PASI':['date':'1981-01-01T00:00:00Z', 'value':'2.5']}",
			value = "Evolución de los índices del paciente")
	Map<String, List<GraphHealthOutcomeDTO>> indicesEvolution;

	@ApiModelProperty(position = 20,
			example = "{}",
			value = "Lista de tratamientos del paciente")
	List<TreatmentDTO> treatments;

}
