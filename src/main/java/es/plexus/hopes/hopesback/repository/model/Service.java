package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "services")
public class Service {
    @Id
    @Column(name = "srv_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "srv_name", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "srv_description", nullable = true, length = 100)
    private String description;

    @OneToOne
    @JoinColumn(name="dct_service_id")
    private Doctor doctor;

}
