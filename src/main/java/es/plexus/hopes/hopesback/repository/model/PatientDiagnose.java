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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "pdg_cin_id", referencedColumnName = "cin_id")
    private Cie9 cie9;

    @ManyToOne
    @JoinColumn(name = "pdg_cid_id", referencedColumnName = "cid_id")
    private Cie10 cie10;

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

    @OneToMany(mappedBy = "patientDiagnose")
    private List<PatientTreatment> treatments;
}
