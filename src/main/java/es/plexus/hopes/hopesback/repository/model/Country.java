package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "pai_code", nullable = false, length = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String code;

    @Basic
    @Column(name = "pai_name", nullable = false, length = 60)
    private String name;



}
