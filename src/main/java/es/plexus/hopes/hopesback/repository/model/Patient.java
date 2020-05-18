package es.plexus.hopes.hopesback.repository.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
@Entity
@Table(name = "patients" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "pac_nhc"),
        @UniqueConstraint(columnNames = "pac_health_card")})
public class Patient {
    @Id
    @Column(name = "pac_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "pac_name", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "pac_first_surname", nullable = false, length = 50)
    private String firstSurname;

    @Basic
    @Column(name = "pac_last_surname", nullable = true, length = 50)
    private String lastSurname;

    @Basic
    @Column(name = "pac_nhc", nullable = false, length = 50)
    private String nhc;

    @Basic
    @Column(name = "pac_health_card", nullable = false, length = 50)
    private String healthCard;

    @Basic
    @Column(name = "pac_dni", nullable = true, length = 9)
    private String dni;

    @Basic
    @Column(name = "pac_address", nullable = true, length = 200)
    private String address;

    @Basic
    @Column(name = "pac_phone", nullable = true, length = 15)
    private String phone;

    @Basic
    @Column(name = "pac_email", nullable = true, length = 50)
    @Email
    private String email;

    @Basic
    @Column(name = "pac_birth_date", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime birthDate;

    @ManyToOne
    @JoinColumn(name = "pac_hos_id", referencedColumnName = "hos_id")
    private Hospital hospital;

    @Basic
    @Column(name = "pac_gender_code", nullable = false, length = 1)
    private String genderCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patients_pathologies",
            joinColumns = @JoinColumn(name = "pcp_pac_id"),
            inverseJoinColumns = @JoinColumn(name = "pcp_pth_id"))
    private Set<Pathology> pathologies = new HashSet<>();

}
