package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    @Column(name = "med_code_act", nullable = false, length = 7)
    private String codeAct;

    @Basic
    @Column(name = "med_recommended", nullable = false)
    private boolean recommended;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "med_recommendation_id", referencedColumnName = "rec_id")
    private Recommendation recommendation;

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
    @Column(name = "med_commercialization", nullable = false)
    private boolean commercialization;
    
    @Basic
    @Column(name = "med_biologic", nullable = false)
    private boolean biologic;

}
