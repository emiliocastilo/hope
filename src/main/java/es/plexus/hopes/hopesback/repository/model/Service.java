package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
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
@Table(name = "services")
public class Service {
	@Id
	@Column(name = "srv_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(name = "srv_name", nullable = false, length = 50)
	private String name;

	@Basic
	@Column(name = "srv_description", length = 100)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "services_pathologies",
			joinColumns = @JoinColumn(name = "srp_srv_id"),
			inverseJoinColumns = @JoinColumn(name = "srp_pth_id"))
	private Set<Pathology> pathologies = new HashSet<>();

}
