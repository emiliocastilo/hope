package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
