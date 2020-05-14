package es.plexus.hopes.hopesback.repository.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "health_outcomes")
public class HealthOutcome {
    @Id
    @Column(name = "hou_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "hou_pac_id", referencedColumnName = "pac_id")
	private Patient patient;
    
    @Basic
    @Column(name = "hou_index_type", nullable = false, length = 50)
    private String indexType;

    @Basic
    @Column(name = "hou_value", nullable = false, length = 50)
    private String value;
    
    @Basic
    @Column(name = "hou_result", nullable = false, length = 50)
    private String result;
    
    @Basic
    @Column(name = "hou_date", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

}
