package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("select pac from Patient pac join pac.pathologies pat WHERE pat.id = :id")
	Page<Patient> findByPathologies(@Param("id")Long id, Pageable pageable);
	
	@Query("select pac from Patient pac join pac.pathologies pat WHERE pat.id = :id "
			+ " AND (LOWER(pac.name) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.firstSurname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.lastSurname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.nhc) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.healthCard) like CONCAT('%',LOWER(:search),'%')) ")
	Page<Patient> findPatientBySearch(@Param("id")Long id, @Param("search")String search, Pageable pageable);
}

