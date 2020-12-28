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
@Table(name = "indications")
public class Indication {
    @Id
    @Column(name = "ind_id", nullable = false, length = 3)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "ind_description", nullable = false, length = 50)
    private String description;

    @Basic
    @Column(name = "ind_pth_id", nullable = false)
    private Long pathologyId;

    @Column(name = "ind_code", nullable = false, unique = true)
    private String code;
}
