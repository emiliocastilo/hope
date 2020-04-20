package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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
    private Short order;

    @Basic
    @Column(name = "sec_icon", nullable = true, length = 100)
    private String icon;

    @Basic
    @Column(name = "sec_url", nullable = true, length = 100)
    private String url;

    @ManyToOne
    @JoinColumn(name = "sec_section_root", referencedColumnName = "sec_id")
    private Section fatherSection;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sections_forms",
            joinColumns = @JoinColumn(name = "scf_section_id"),
            inverseJoinColumns = {@JoinColumn(name = "scf_form_id")})
    private Set<Form> forms = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sections_roles",
            joinColumns = @JoinColumn(name = "scr_section_id"),
            inverseJoinColumns = {@JoinColumn(name = "scr_role_id")})
    private Set<Role> roles = new HashSet<>();

}
