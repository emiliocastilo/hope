package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

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

}
