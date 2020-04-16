package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "hospitals", schema = "public", catalog = "hopes_back")
public class Hospital {
    @Id
    @Column(name = "hos_id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "hos_name", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hospitals_pathologies",
            joinColumns = @JoinColumn(name = "hsp_hos_id"),
            inverseJoinColumns = @JoinColumn(name = "hsp_pth_id"))
    private Set<Pathology> pathologies = new HashSet<>();
}
