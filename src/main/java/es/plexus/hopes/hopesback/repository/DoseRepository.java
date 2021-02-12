package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Long> {

    List<Dose> findByCodeAtc(String codeAtc);

    Optional<Dose> findByCodeAtcAndDescriptionAndDoseIndicated(String codeAtc, String description, String doseIndicated);
}
