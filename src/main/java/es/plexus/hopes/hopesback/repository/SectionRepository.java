package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {



}
