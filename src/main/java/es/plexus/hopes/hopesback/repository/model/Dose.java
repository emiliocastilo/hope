package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "doses")
public class Dose extends AbstractAudit {

    @Id
    @Column(name = "dos_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "dos_code_atc", nullable = false, length = 7)
    private String codeAtc;

    @Basic
    @Column(name = "dos_description", length = 150)
    private String description;

    @Basic
    @Column(name = "dos_dose_indicated", length = 150)
    private String doseIndicated;

    @Basic
    @Column(name = "dos_recommendation", length = 200)
    private String recommendation;
}
