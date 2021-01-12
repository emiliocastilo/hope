package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patients_clinical_data")
public class PatientClinicalData {
    @Id
    @Column(name = "pcd_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pcd_pac_id", referencedColumnName = "pac_id")
    private Patient patient;

    @Column(name = "pcd_name")
    private String name;

    @Column(name = "pcd_value")
    private String value;

    @Column(name = "pcd_description")
    private String description;

    @Column(name = "pcd_classification")
    private String classification;

    @Basic
    @Column(name = "pcd_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    public PatientClinicalData(Long id, Long patientId, String name, String value, String description, String classification, LocalDateTime date) {
        Patient patientAux = new Patient();
        patientAux.setId(patientId);
        this.id = id;
        this.patient = patientAux;
        this.name = name;
        this.value = value;
        this.description = description;
        this.classification = classification;
        this.date = date;
    }

    public PatientClinicalData() {
    }
}
