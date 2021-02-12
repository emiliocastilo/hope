package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "patients_treatments_lines")
public class PatientTreatmentLine {
    @Id
    @Column(name = "ptl_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "ptl_ptr_id", referencedColumnName = "ptr_id")
	private PatientTreatment patientTreatment;

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
    @Column(name = "ptr_regimen", nullable = false, length = 50)
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
}
