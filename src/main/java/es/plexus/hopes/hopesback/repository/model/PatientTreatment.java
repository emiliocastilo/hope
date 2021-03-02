package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "patients_treatments")
public class PatientTreatment {
	@Id
	@Column(name = "ptr_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ptr_pdg_id", referencedColumnName = "pdg_id")
	private PatientDiagnose patientDiagnose;

	@Basic
	@Column(name = "ptr_active", nullable = false)
	private boolean active;

	@Basic
	@Column(name = "ptr_type", nullable = false, length = 50)
	private String type;

	@ManyToOne
	@JoinColumn(name = "ptr_med_id", referencedColumnName = "med_id")
	private Medicine medicine;

	@Basic
	@Column(name = "ptr_dose", nullable = false, length = 50)
	private String dose;

	@Basic
	@Column(name = "ptr_master_formula", nullable = false, length = 50)
	private String masterFormula;

	@Basic
	@Column(name = "ptr_master_formula_dose", nullable = false, length = 50)
	private String masterFormulaDose;

	@Basic
	@Column(name = "ptr_regimen", nullable = false, length = 50)
	private String regimen;

	@Basic
	@Column(name = "ptr_init_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime initDate;

	@Basic
	@Column(name = "ptr_final_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime finalDate;

	@Basic
	@Column(name = "ptr_end_cause", nullable = false, length = 50)
	private String endCause;

	@Basic
	@Column(name = "ptr_reason", nullable = false, length = 50)
	private String reason;

	@OneToMany(mappedBy = "patientTreatment")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<PatientTreatmentLine> treatmentLines;






	@Basic
	@Column(name = "ptr_psychological_impact", nullable = false)
	private Boolean psychologicalImpact;

	@Basic
	@Column(name = "ptr_date_prescription", columnDefinition = "TIMESTAMP")
	private LocalDateTime datePrescription;

	@Basic
	@Column(name = "ptr_expected_end_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime expectedEndDate;

	@Basic
	@Column(name = "ptr_observations", nullable = false, length = 255)
	private String observations;

	@Basic
	@Column(name = "ptr_other_dose", nullable = false, length = 255)
	private String otherDose;

	@Basic
	@Column(name = "ptr_treatment_continue", nullable = false)
	private Boolean treatmentContinue;

	@Basic
	@Column(name = "ptr_special_indication", nullable = false)
	private Boolean specialIndication;

	@Basic
	@Column(name = "ptr_visible_injury", nullable = false)
	private Boolean visibleInjury;

	@Basic
	@Column(name = "ptr_pulsatile_treatment", nullable = false)
	private Boolean pulsatileTreatment;

	@Basic
	@Column(name = "ptr_other", nullable = false, length = 50)
	private String other;

	@Basic
	@Column(name = "ptr_suspension_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime suspensionDate;

	public PatientTreatmentLine getActiveLine(){
		return this.getTreatmentLines().stream().filter(patientTreatmentLine -> patientTreatmentLine.getActive().equals(true)).findFirst().orElse(null);
	}
}
