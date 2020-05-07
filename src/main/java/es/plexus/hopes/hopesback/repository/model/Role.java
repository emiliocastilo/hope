package es.plexus.hopes.hopesback.repository.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "rol_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "rol_name", length = 50)
	private String name;

	@Column(name = "rol_description", length = 500)
	private String description;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	@MapKey(name = "locale")
    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    private List<LocalizedRole> localizations = new ArrayList<>();
 	
}
