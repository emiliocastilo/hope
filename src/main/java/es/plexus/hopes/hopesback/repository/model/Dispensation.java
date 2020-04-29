package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dispensations")
public class Dispensation extends AbstractAudit {
	@Id
	@Column(name = "dsp_id", nullable = false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(name = "dsp_date", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime date;

	@Basic
	@Column(name = "dsp_start_period", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime startPeriod;

	@Basic
	@Column(name = "dsp_end_period", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime endPeriod;

	@Basic
	@Column(name = "dsp_num_records", nullable = false)
	private int numRecords;
}
