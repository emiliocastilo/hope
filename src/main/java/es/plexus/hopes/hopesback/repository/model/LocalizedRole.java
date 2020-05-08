package es.plexus.hopes.hopesback.repository.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Data
@Entity

@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "localized_roles")
public class LocalizedRole {
    
	@Id
	@Column(name = "lcr_id", nullable = false)
	private Long id;
	
	@Basic
	@Column(name = "lcr_locale", nullable = false, length = 2)
	private String locale;
	
    @Basic
    @Column(name = "lcr_name", nullable = false, length = 60)
    private String name;
    
    @Basic
    @Column(name = "lcr_description", nullable = false, length = 200)
    private String description;

    @ManyToOne
	@JoinColumn(name = "lcr_rol_id", referencedColumnName = "rol_id")
	private Role role;    
}
