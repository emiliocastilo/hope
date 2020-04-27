package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @Column(name = "med_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "med_act_ingredients", nullable = false, length = 500)
    private String actIngredients;

    @Basic
    @Column(name = "med_code_act", nullable = false, length = 7)
    private String codeAct;

    @Basic
    @Column(name = "med_recommended", nullable = true)
    private Boolean recommended;

    @ManyToOne
    @JoinColumn(name = "med_recommendation_id", referencedColumnName = "rec_id")
    private Recommendation recommendation;

}
