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

import lombok.Getter;

@Getter
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
    
    @Basic
    @Column(name = "pdg_indication", nullable = false, length = 50)
    private String indication;

    @Basic
    @Column(name = "pdg_cie9_code", nullable = false, length = 50)
    private String cie9Code;

    @Basic
    @Column(name = "pdg_cie9_desc", length = 50)
    private String cie9Desc;

    @Basic
    @Column(name = "pdg_cie10_code", nullable = false, length = 50)
    private String cie10Code;

    @Basic
    @Column(name = "pdg_cie10_desc", nullable = false, length = 50)
    private String cie10Desc;

    @Basic
    @Column(name = "pdg_others_indications", length = 50)
    private String othersIndications; 

    @Basic
    @Column(name = "pdg_init_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime initDate;
    
    @Basic
    @Column(name = "pdg_symptoms_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime symptomsDate;
    
    @Basic
    @Column(name = "pdg_derivation_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime derivationDate;

}
