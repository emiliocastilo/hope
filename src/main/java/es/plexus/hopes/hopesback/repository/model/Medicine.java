package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "medicines")
public class Medicine extends AbstractAudit {
    @Id
    @Column(name = "med_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "med_act_ingredients", nullable = false, length = 500)
    private String actIngredients;

    @Basic
    @Column(name = "med_code_atc", nullable = false, length = 7)
    private String codeAtc;

    @Column(name = "med_code_atc_type", nullable = false, length = 100)
    private String codeAtcType;

    @Basic
    @Column(name = "med_acronym", length = 15)
    private String acronym;

    @Basic
    @Column(name = "med_national_code", nullable = false, length = 7, unique = true)
    private String nationalCode;

    @Basic
    @Column(name = "med_description", length = 150)
    private String description;

    @Basic
    @Column(name = "med_presentation", length = 500)
    private String presentation;

    @Basic
    @Column(name = "med_content", length = 150)
    private String content;

    @Basic
    @Column(name = "med_authorization_date")
    private LocalDate authorizationDate;

    @Basic
    @Column(name = "med_authorized", nullable = false)
    private boolean authorized;

    @Basic
    @Column(name = "med_end_date_authorization")
    private LocalDate endDateAuthorization;

    @Basic
    @Column(name = "med_commercialization", nullable = false)
    private boolean commercialization;

    @Basic
    @Column(name = "med_commercialization_date")
    private LocalDate commercializationDate;

    @Basic
    @Column(name = "med_end_date_commercialization")
    private LocalDate endDateCommercialization;

    @Basic
    @Column(name = "med_via_administration", length = 150)
    private String viaAdministration;

    @Basic
    @Column(name = "med_brand", length = 50)
    private String brand;

    @Basic
    @Column(name = "med_units")
    private BigDecimal units;

    @Basic
    @Column(name = "med_pvl", scale = 13, precision = 2)
    private BigDecimal pvl;

    @Basic
    @Column(name = "med_pvl_unitary", scale = 13, precision = 2)
    private BigDecimal pvlUnitary;

    @Basic
    @Column(name = "med_pvp", scale = 13, precision = 2)
    private BigDecimal pvp;

    @Basic
    @Column(name = "med_biologic", nullable = false)
    private boolean biologic;

    @Basic
    @Column(name = "med_family", length = 150)
    private String family;

    @Basic
    @Column(name = "med_subfamily", length = 150)
    private String subfamily;

    @Column(name = "med_unit_dose")
    private BigDecimal unitDose;

    @Column(name = "med_treatment_type", length = 150)
    private String treatmentType;

    @ManyToMany
    @JoinTable(name = "medicines_pathologies",
            joinColumns = @JoinColumn(name = "mdp_med_id"),
            inverseJoinColumns = @JoinColumn(name = "mdp_pth_id"))
    private Set<Pathology> pathologies = new HashSet<>();

}
