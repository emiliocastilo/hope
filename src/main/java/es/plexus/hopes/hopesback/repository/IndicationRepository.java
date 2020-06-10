/**
 * 
 */
package es.plexus.hopes.hopesback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.repository.model.Indication;

/**
 * @author jose.estevezbarroso
 *
 */
@Repository
public interface IndicationRepository extends JpaRepository<Indication, Long> {

	Optional<Indication> findByDescription(final String description);
}
