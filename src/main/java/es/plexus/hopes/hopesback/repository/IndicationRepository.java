/**
 *
 */
package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Indication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author jose.estevezbarroso
 *
 */
@Repository
public interface IndicationRepository extends JpaRepository<Indication, Long> {

    Optional<Indication> findByDescription(final String description);

	Optional<Indication> findById(final Long id);

    List<Indication> findByPathologyId(Long id);

    Optional<Indication> findByCode(final String code);
}
