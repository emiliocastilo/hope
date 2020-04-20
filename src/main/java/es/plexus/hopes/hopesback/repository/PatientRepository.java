package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("select pac from Patient pac join pac.pathologies pat WHERE pat.id = :id")
	List<Patient> findByPathologies(@Param("id")Long id);

}

