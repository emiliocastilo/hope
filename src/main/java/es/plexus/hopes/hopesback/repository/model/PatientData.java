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
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patients_data")
public class PatientData {

	@Id
	@Column(name = "pdt_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pdt_pac_id", referencedColumnName = "pac_id")
	private Patient patient;

	@Basic
	@Column(name = "pdt_weight", precision = 2)
	private Double weight;

	@Basic
	@Column(name = "pdt_height", precision = 2)
	private Double height;

	@Basic
	@Column(name = "pdt_imc", precision = 2)
	private Double imc;

	@Basic
	@Column(name = "pdt_pat")
	private Long pat;

	@Basic
	@Column(name = "pdt_pas")
	private Long pas;

	@Basic
	@Column(name = "pdt_abdominal_perimeter")
	private Long abdominalPerimeter;

	@Basic
	@Column(name = "pdt_body_surface", precision = 2)
	private Double bodySurface;

	@Basic
	@Column(name = "pdt_psoriatric", nullable = false)
	private boolean psoriatric;

	@Basic
	@Column(name = "pdt_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime date;

}
