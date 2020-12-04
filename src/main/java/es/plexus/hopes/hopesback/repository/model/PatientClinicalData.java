package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

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
}
