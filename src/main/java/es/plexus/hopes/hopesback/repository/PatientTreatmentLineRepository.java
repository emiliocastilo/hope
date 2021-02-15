package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.*;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientTreatmentLineRepository extends JpaRepository<PatientTreatmentLine, Long> {

    List<PatientTreatmentLine> findByPatientTreatment(PatientTreatment patientTreatment);

}

