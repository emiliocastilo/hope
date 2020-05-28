package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "photos")
public class Photo extends AbstractAudit {

	@Id
	@Column(name = "pho_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "pho_name")
	private String name;

	@Column(name = "pho_type_photo")
	private String typePhoto;

	@Lob
	@Column(name = "pho_photo_bytes", columnDefinition = "BLOB")
	private String photoBytes;

	@Column(name = "pho_title")
	private String title;

	@Column(name = "pho_description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "pho_pth_id", referencedColumnName = "pth_id")
	private Pathology pathology;

	@ManyToOne
	@JoinColumn(name = "pho_pac_id", referencedColumnName = "pac_id")
	private Patient patient;

	@Column(name = "user_updated")
	private String username;

}