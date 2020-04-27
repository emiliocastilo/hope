package es.plexus.hopes.hopesback.repository.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "sections_forms", schema = "public", catalog = "hopes_back")
public class SectionForm {
    private Integer id;
    private Short order;
    private Section sectionsByScfSectionId;
    private Form formsByScfFormId;

    @Id
    @Column(name = "scf_id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "scf_order", nullable = true)
    public Short getOrder() {
        return order;
    }

    public void setOrder(Short order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionForm that = (SectionForm) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }

    @ManyToOne
    @JoinColumn(name = "scf_section_id", referencedColumnName = "sec_id", nullable = false)
    public Section getSectionsByScfSectionId() {
        return sectionsByScfSectionId;
    }

    public void setSectionsByScfSectionId(Section sectionsByScfSectionId) {
        this.sectionsByScfSectionId = sectionsByScfSectionId;
    }

    @ManyToOne
    @JoinColumn(name = "scf_form_id", referencedColumnName = "for_id", nullable = false)
    public Form getFormsByScfFormId() {
        return formsByScfFormId;
    }

    public void setFormsByScfFormId(Form formsByScfFormId) {
        this.formsByScfFormId = formsByScfFormId;
    }
}
