package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {


    List<Section> findByMenuTrue();
}
