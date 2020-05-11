package es.plexus.hopes.hopesback.repository.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Data
@Entity

@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "localized_sections")
public class LocalizedSection {
    
	@Id
	@Column(name = "lcs_id", nullable = false)
	private Long id;
	
	@Basic
	@Column(name = "lcs_locale", nullable = false, length = 2)
	private String locale;
	
    @Basic
    @Column(name = "lcs_title", nullable = false, length = 60)
    private String title;
    
    @Basic
    @Column(name = "lcs_description", nullable = false, length = 200)
    private String description;

    @ManyToOne
    //@MapsId("id")
	@JoinColumn(name = "lcs_sec_id", referencedColumnName = "sec_id")
	private Section section;    
}
