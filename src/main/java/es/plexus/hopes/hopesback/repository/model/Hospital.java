package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "hospitals", schema = "hopes", catalog = "hopes_back")
public class Hospital {
	@Id
	@Column(name = "hos_id", nullable = false)
	private Long id;

	@Basic
	@Column(name = "hos_name", nullable = false, length = 100)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "hospitals_pathologies",
			joinColumns = @JoinColumn(name = "hsp_hos_id"),
			inverseJoinColumns = @JoinColumn(name = "hsp_pth_id"))
	private Set<Pathology> pathologies = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "hospitals_services", joinColumns = @JoinColumn(name = "hss_hos_id"),
	inverseJoinColumns = @JoinColumn(name = "hss_srv_id"))
	private Set<Service> services = new HashSet<>();

}
