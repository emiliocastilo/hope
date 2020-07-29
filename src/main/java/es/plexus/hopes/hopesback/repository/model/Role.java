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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_hospitals",
			joinColumns = @JoinColumn(name = "rhs_rol_id"),
			inverseJoinColumns = @JoinColumn(name = "rhs_hos_id"))
	private Set<Hospital> hospitals = new HashSet<>();

}
