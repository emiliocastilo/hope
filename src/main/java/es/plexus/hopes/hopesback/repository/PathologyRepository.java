package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PathologyRepository extends JpaRepository<Pathology, Long> {
    Optional<Pathology> findByName(String name);
}
