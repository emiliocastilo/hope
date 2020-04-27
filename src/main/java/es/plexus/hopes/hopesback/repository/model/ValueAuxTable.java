package es.plexus.hopes.hopesback.repository.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "values_aux_tables")
public class ValueAuxTable {
    @Id
    @Column(name = "vat_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Basic
    @Column(name = "vat_aux_table_id", nullable = false)
    private Short auxTableId;

    @Basic
    @Column(name = "vat_aux_table_name", nullable = false, length = 50)
    private String auxTableName;

    @Basic
    @Column(name = "vat_aux_table_desc", nullable = true, length = 150)
    private String auxTableDesc;

    @Basic
    @Column(name = "vat_value_id", nullable = false)
    private Short valueId;

    @Basic
    @Column(name = "vat_value", nullable = true, length = 2000)
    private String value;

    @Basic
    @Column(name = "vat_order", nullable = true)
    private Short order;

    @Basic
    @Column(name = "vat_active", nullable = true)
    private Boolean active;
}
