package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patients_treatments_lines")
public class PatientTreatmentLine {
    @Id
    @Column(name = "ptl_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "ptl_ptr_id", nullable = false)
	private Long patientTreatment;

    @Basic
	@Column(name = "ptl_modification_count", nullable = false)
	private Long modificationCount;

    @Basic
    @Column(name = "ptl_type", nullable = false, length = 50)
    private String type;

    @ManyToOne
	@JoinColumn(name = "ptl_med_id", referencedColumnName = "med_id")
	private Medicine medicine;
    
    @Basic
    @Column(name = "ptl_dose", nullable = true, length = 50)
    private String dose;
    
    @Basic
    @Column(name = "ptl_master_formula", nullable = false, length = 50)
    private String masterFormula;
    
    @Basic
    @Column(name = "ptl_master_formula_dose", nullable = false, length = 50)
    private String masterFormulaDose;

    @Basic
    @Column(name = "ptl_regimen", nullable = false, length = 50)
    private String regimen;
    
    @Basic
    @Column(name = "ptl_reason", nullable = false, length = 50)
    private String reason;

    @Basic
    @Column(name = "ptl_med_changed", nullable = false)
    private Boolean hadMedicineChange;

    @Basic
    @Column(name = "ptl_active", nullable = false)
    private Boolean active;

    @Basic
    @Column(name = "ptl_deleted", nullable = false)
    private Boolean deleted;

    @Basic
    @Column(name = "ptl_deletion_date", nullable = true)
    private LocalDateTime deletionDate;

    @Basic
    @Column(name = "ptl_suspension_date", nullable = true)
    private LocalDateTime suspensionDate;


    @Basic
    @Column(name = "ptl_psychological_impact", nullable = true)
    private Boolean psychologicalImpact;

    @Basic
    @Column(name = "ptl_treatment_continue", nullable = true)
    private Boolean treatmentContinue;

    @Basic
    @Column(name = "ptl_visible_injury", nullable = true)
    private Boolean visibleInjury;

    @Basic
    @Column(name = "ptl_pulsatile_tratment", nullable = true)
    private Boolean pulsatileTreatment;

    @Basic
    @Column(name = "ptl_special_indication", nullable = true)
    private Boolean specialIndication;

    @Basic
    @Column(name = "ptl_init_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime initDate;

    @Basic
    @Column(name = "ptl_final_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime finalDate;

    @Basic
    @Column(name = "ptl_expected_end_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime expectedEndDate;

    @Basic
    @Column(name = "ptl_date_prescription", columnDefinition = "TIMESTAMP")
    private LocalDateTime datePrescription;

    @Basic
    @Column(name = "ptl_observations", nullable = false, length = 255)
    private String observations;

    @Basic
    @Column(name = "ptl_other", nullable = false, length = 50)
    private String other;
    
    @Basic
    @Column(name = "ptl_other_dose", nullable = false, length = 50)
    private String otherDose;

}
