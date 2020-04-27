package es.plexus.hopes.hopesback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.repository.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("select pac from Patient pac join pac.pathologies pat WHERE pat.id = :id")
	Page<Patient> findByPathologies(@Param("id")Long id, Pageable pageable);
	
	@Query("select pac from Patient pac "
			+ " WHERE LOWER(pac.name) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.firstSurname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.lastSurname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.nhc) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.healthCard) like CONCAT('%',LOWER(:search),'%') ")
	Page<Patient> findPatientBySearch(@Param("search")String search, Pageable pageable);
}

