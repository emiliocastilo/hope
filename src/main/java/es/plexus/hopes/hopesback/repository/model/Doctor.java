package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "doctors")
public class Doctor extends AbstractAudit {

	@Id
	@Column(name = "dct_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(name = "dct_name", nullable = false, length = 50)
	private String name;

	@Basic
	@Column(name = "dct_surname", nullable = false, length = 100)
	private String surname;

	@Basic
	@Column(name = "dct_phone", nullable = false, length = 11)
	private String phone;

	@Basic
	@Column(name = "dct_dni", nullable = false, length = 9)
	private String dni;

	@Basic
	@Column(name = "dct_college_number", nullable = false)
	private Long collegeNumber;

	@Basic
	@Column(name = "dct_active", nullable = false)
	private Boolean active = true;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dct_user_id", referencedColumnName = "usr_id")
	private User user;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "dct_service_id", referencedColumnName = "srv_id")
	private Service service;
}
