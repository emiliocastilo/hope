package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cies_nine")
public class Cie9 {

	@Id
	@Column(name = "cin_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(name = "cin_code", nullable = false, length = 20)
	private String code;

	@Basic
	@Column(name = "cin_description", nullable = false, length = 200)
	private String description;

}
