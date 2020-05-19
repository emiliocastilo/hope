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
    @Column(name = "ind_code", nullable = false, length = 3)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @Basic
    @Column(name = "ind_description", nullable = false, length = 20)
    private String descripcion;



}
