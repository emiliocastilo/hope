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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dispensation_details")
public class DispensationDetail extends AbstractAudit {
	@Id
	@Column(name = "dsd_id", nullable = false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(name = "dsd_date", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime date;

	@Basic
	@Column(name = "dsd_nhc", nullable = true, length = 50)
	private String nhc;

	@Basic
	@Column(name = "dsd_code", nullable = true, length = 9)
	private String code;

	@Basic
	@Column(name = "dsd_national_code", nullable = true, length = 6)
	private Integer nationalCode;

	@Basic
	@Column(name = "dsd_description", nullable = true, length = 100)
	private String description;

	@Basic
	@Column(name = "dsd_quantity", nullable = true, length = 100)
	private String quantity;

	@Basic
	@Column(name = "dsd_amount", nullable = true, scale = 13, precision = 2)
	private BigDecimal amount;

	@Basic
	@Column(name = "dsd_days_disp", nullable = true)
	private Integer daysDispensation;

	@ManyToOne
	@JoinColumn(name = "dsd_dsp_id", referencedColumnName = "dsp_id")
	private Dispensation dispensation;

}
