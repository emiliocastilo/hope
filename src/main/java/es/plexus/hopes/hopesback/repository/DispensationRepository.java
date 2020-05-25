package es.plexus.hopes.hopesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.repository.model.Dispensation;

@Repository
public interface DispensationRepository extends JpaRepository<Dispensation, Long> {

}

