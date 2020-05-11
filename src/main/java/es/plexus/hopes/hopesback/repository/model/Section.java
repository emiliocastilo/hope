package es.plexus.hopes.hopesback.repository.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.i18n.LocaleContextHolder;

import lombok.Data;

@Data
@Entity
@Table(name = "sections")
public class Section {

	@Id
	@Column(name = "sec_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(name = "sec_title", nullable = false, length = 50)
	private String title;

	@Basic
	@Column(name = "sec_description", length = 500)
	private String description;

	@Basic
	@Column(name = "sec_active", nullable = false)
	private boolean active;

	@Basic
	@Column(name = "sec_order", nullable = false)
	private int order;

	@Basic
	@Column(name = "sec_icon", length = 100)
	private String icon;

	@Basic
	@Column(name = "sec_url", length = 100)
	private String url;

	@ManyToOne
	@JoinColumn(name = "sec_section_root", referencedColumnName = "sec_id")
	private Section fatherSection;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sections_forms",
			joinColumns = @JoinColumn(name = "scf_section_id"),
			inverseJoinColumns = @JoinColumn(name = "scf_form_id"))
	private Set<Form> forms = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sections_roles",
			joinColumns = @JoinColumn(name = "scr_section_id"),
			inverseJoinColumns = @JoinColumn(name = "scr_role_id"))
	private Set<Role> roles;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	@MapKey(name = "locale")
    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    private Map<String, LocalizedSection> localizations = new HashMap<>();

	public LocalizedSection getLocale() {
		LocalizedSection localizedSection = localizations.get(LocaleContextHolder.getLocale().getLanguage());
		return localizedSection;
    }
 
	public String getTitle() { 	
        return this.getLocale()!=null?this.getLocale().getTitle():title;
    }
	
    public String getDescription() {
    	return this.getLocale()!=null?this.getLocale().getDescription():description;
    }
	
}
