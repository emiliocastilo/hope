package es.plexus.hopes.hopesback.repository.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genders")
public class Gender {
    @Id
    @Column(name = "gnd_code", nullable = false, length = 1)
    private String code;

    @Basic
    @Column(name = "gnd_name", nullable = false, length = 9)
    private String name;
}
