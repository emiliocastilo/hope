package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sections")
public class Section {

    @Id
    @Column(name = "sec_id", nullable = false)
    private int id;

    @Basic
    @Column(name = "sec_title", nullable = false, length = 50)
    private String title;

    @Basic
    @Column(name = "sec_description", nullable = true, length = 500)
    private String description;

    @Basic
    @Column(name = "sec_menu", nullable = true)
    private Boolean menu;

    @Basic
    @Column(name = "sec_order", nullable = false)
    private short order;
}
