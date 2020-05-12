package es.plexus.hopes.hopesback.repository.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "patients_treatments")
public class PatientTreatment {
    @Id
    @Column(name = "ptr_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "ptr_pac_id", referencedColumnName = "pac_id")
	private Patient patient;
    
    @ManyToOne
	@JoinColumn(name = "ptr_pdg_id", referencedColumnName = "pdg_id")
	private PatientDiagnose patientDiagnose;
    
    @Basic
	@Column(name = "ptr_active", nullable = false)
	private String active;
    
    @Basic
    @Column(name = "ptr_indication", nullable = false, length = 50)
    private String indication;

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
    @Column(name = "ptr_init_date", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime initDate;
    
    @Basic
    @Column(name = "ptr_final_date", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime finalDate;
    
    @Basic
    @Column(name = "ptr_end_cause", nullable = false, length = 50)
    private String endCause;
    
    @Basic
    @Column(name = "ptr_reason", nullable = false, length = 50)
    private String reason;

 
}
