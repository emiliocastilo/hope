package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<PatientDiagnose, Long> {

}

