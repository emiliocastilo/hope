package es.plexus.hopes.hopesback.repository.model;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAudit {

	@Column(name = "date_created", updatable = false)
	@CreatedDate
	@EqualsAndHashCode.Exclude
	private LocalDateTime dateCreated;

	@Column(name = "date_updated")
	@LastModifiedDate
	@EqualsAndHashCode.Exclude
	private LocalDateTime dateUpdated;
}