package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "rol_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
	@GenericGenerator(name = "seq", strategy = "increment")
	private Long id;

	@Column(name = "rol_name", length = 50)
	private String name;

	@Column(name = "rol_description", length = 500)
	private String description;

	@Column(name = "rol_code", length = 100)
	private String code;

	@ManyToOne
	@JoinColumn(name = "rol_hos_id", referencedColumnName = "hos_id")
	private Hospital hospital;

	@ManyToOne
	@JoinColumn(name = "rol_srv_id", referencedColumnName = "srv_id")
	private Service service;

	@ManyToOne
	@JoinColumn(name = "rol_pth_id", referencedColumnName = "pth_id")
	private Pathology pathology;

}
