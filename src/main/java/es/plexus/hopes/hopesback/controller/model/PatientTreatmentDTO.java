package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@ApiModel
public class PatientTreatmentDTO {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "diagnostico 1", value = "Diagnóstico asociado al tratamiento")
	private PatientDiagnosisDTO patientDiagnose;

	@ApiModelProperty(position = 30, example = "true", value = "Tratamiento activo")
	private boolean active;

	@ApiModelProperty(position = 40, example = "BIOLOGICO", value = "Tipo")
	private String type;

	@ApiModelProperty(position = 50, example = "medicamento 1", value = "Medicamento asociado al tratamiento")
	private MedicineDTO medicine;

	@ApiModelProperty(position = 60, example = "2mg/dia", value = "Dosis")
	private String dose;

	@ApiModelProperty(position = 70, example = "mágia negra", value = "Formula maestra")
	private String masterFormula;

	@ApiModelProperty(position = 80, example = "2mg/dia", value = "Dosis de la formula maestra")
	private String masterFormulaDose;

	@ApiModelProperty(position = 90, example = "INTENSIFICADA", value = "Régimen del tratamiento")
	private String regimen;

	@ApiModelProperty(position = 100, example = "1981-01-01T00:00:00Z", value = "Fecha de inicio del tratamiento")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime initDate;

	@ApiModelProperty(position = 110, example = "1981-01-01T00:00:00Z", value = "Fecha de fin del tratamiento")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime finalDate;

	@ApiModelProperty(position = 120, example = "Cambio", value = "Cambio o suspensión")
	private String endCause;

	@ApiModelProperty(position = 130, example = "Ineficacia", value = "Razón del cambio o la suspensión del tratamiento")
	private String reason;

	@ApiModelProperty(position = 140, example = "Ineficacia", value = "Lineas con detalles del tratamiento")
	private List<PatientTreatmentLineDTO> lines;

	@ApiModelProperty(position = 150, example = "true", value = "impacto psicológico")
	private boolean psychologicalImpact;

	@ApiModelProperty(position = 160, example = "1981-01-01T00:00:00Z", value = "Fecha prescripción")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime datePrescription;

	@ApiModelProperty(position = 160, example = "1981-01-01T00:00:00Z", value = "Fecha estimada de fin de tratamiento")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime expectedEndDate;

	@ApiModelProperty(position = 170, example = "Observacion", value = "Observacion")
	private String observations;

	@ApiModelProperty(position = 180, example = "Detalle dosis", value = "Detalle dosis.")
	private String otherDose;

	@ApiModelProperty(position = 190, example = "true", value = "Trantamiento continuado")
	private boolean treatmentContinue;

	@ApiModelProperty(position = 200, example = "true", value = "Daños visibles.")
	private boolean visibleInjury;

	@ApiModelProperty(position = 210, example = "true", value = "Tratamiento pulsátil")
	private boolean pulsatileTreatment;

	@ApiModelProperty(position = 220, example = "otras observaciones", value = "otras observaciones")
	private String other;

	@ApiModelProperty(position = 230, example = "1981-01-01T00:00:00Z", value = "Fecha de inicio del tratamiento")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime suspensionDate;

	public PatientTreatmentLineDTO getActivePatientTreatmentLine() {
		return this.lines.stream().filter(patientTreatmentLineDTO -> isActive()).findFirst().orElse(null);
	}
}
