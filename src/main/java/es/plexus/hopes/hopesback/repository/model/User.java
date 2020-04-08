package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "usr_name"),
        @UniqueConstraint(columnNames = "usr_email")})
public class User {

    @Id
    @Column(name="usr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max=500)
    @Column(name ="usr_name")
    private String username;

    @NotBlank
    @Size(max=200)
    @Column(name ="usr_password")
    private String password;

    @NotBlank
    @Size(max=50)
    @Column(name ="usr_email")
    @Email
    private String email;

    @Column(name = "usr_date_crea", columnDefinition = "TIMESTAMP")
    private LocalDate dateCreation;

    @Column(name="usr_date_mod", columnDefinition = "TIMESTAMP")
    private LocalDate dateModification;

    @ManyToOne
    @JoinColumn(name="usr_hos_id")
    private Hospital hospital;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "uro_user_id"),
            inverseJoinColumns = @JoinColumn(name = "uro_rol_id"))
    private Set<Role> roles = new HashSet<>();

}
