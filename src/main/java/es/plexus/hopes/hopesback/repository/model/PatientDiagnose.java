package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "pdg_ind_id", referencedColumnName = "ind_id")
    private Indication indication;

    @Column(name = "pdg_cie_code")
    private String cieCode;

    @Column(name = "pdg_cie_description")
    private String cieDescription;

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
