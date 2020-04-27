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
@Table(name = "forms")
public class Form {
    @Id
    @Column(name = "for_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "for_title", nullable = false, length = 50)
    private String title;

    @Basic
    @Column(name = "for_description", nullable = true, length = 500)
    private String description;

    @Basic
    @Column(name = "for_template", nullable = true, length = 100)
    private String template;
}
