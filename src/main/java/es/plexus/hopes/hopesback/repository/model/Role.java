package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name = "rol_traduction", length = 100)
    private String traduction;

}
