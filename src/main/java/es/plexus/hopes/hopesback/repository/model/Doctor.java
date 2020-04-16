package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @Column(name = "dct_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "dct_name", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "dct_surname", nullable = false, length = 100)
    private String surname;

    @Basic
    @Column(name = "dct_phone", nullable = false, length = 11)
    private String phone;

    @Basic
    @Column(name = "dct_dni", nullable = false, length = 9)
    private String dni;

    @Basic
    @Column(name = "dct_college_number", nullable = false)
    private Short collegeNumber;

    @Basic
    @Column(name = "dct_active", nullable = false)
    private Boolean active;

    @Basic
    @Column(name = "dct_date_create", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDate dateCreate;

    @Basic
    @Column(name = "dct_date_modify", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDate dateModify;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dct_user_id", referencedColumnName = "usr_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dct_service_id", referencedColumnName = "srv_id")
    private Service service;

}
