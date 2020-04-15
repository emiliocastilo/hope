package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "pathologies")
public class Pathology {
    @Id
    @Column(name = "pth_id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "pth_name", nullable = false, length = 100)
    private String name;

    @Basic
    @Column(name = "pth_description", nullable = true, length = 500)
    private String description;


}
