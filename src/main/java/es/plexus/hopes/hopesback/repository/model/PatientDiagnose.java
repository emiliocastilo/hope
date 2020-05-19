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
@Table(name = "patients_diagnoses")
public class PatientDiagnose {
    @Id
    @Column(name = "pdg_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "pdg_pac_id", referencedColumnName = "pac_id")
	private Patient patient;
    
    @ManyToOne
	@JoinColumn(name = "pdg_ind_code", referencedColumnName = "ind_code")
	private Indication indication;

    @Basic
    @Column(name = "pdg_cie9_code", nullable = false, length = 50)
    private String cie9Code;

    @Basic
    @Column(name = "pdg_cie9_desc", nullable = true, length = 50)
    private String cie9Desc;

    @Basic
    @Column(name = "pdg_ci10_code", nullable = false, length = 50)
    private String ci10Code;

    @Basic
    @Column(name = "pdg_ci10_desc", nullable = false, length = 50)
    private String ci10Desc;

    @Basic
    @Column(name = "pdg_others_indications", nullable = true, length = 50)
    private String othersIndications; 

    @Basic
    @Column(name = "pdg_init_date", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime initDate;
    
    @Basic
    @Column(name = "pdg_symptoms_date", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime symptomsDate;
    
    @Basic
    @Column(name = "pdg_derivation_date", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime derivationDate;

 
}
