package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
