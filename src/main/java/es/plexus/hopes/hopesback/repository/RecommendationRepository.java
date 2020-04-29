package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}
