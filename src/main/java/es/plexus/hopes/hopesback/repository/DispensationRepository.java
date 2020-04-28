package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Dispensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispensationRepository extends JpaRepository<Dispensation, Long> {

}

