package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("select pac from Patient pac join pac.pathologies pat WHERE pat.id in (:pathologies)")
	Page<Patient> findByPathologies(@Param("pathologies") Collection pathologies, Pageable pageable);
	
	@Query("select pac from Patient pac join pac.pathologies pat WHERE pat.id in (:pathologies) "
			+ " AND (LOWER(pac.name) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.firstSurname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.lastSurname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.nhc) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.healthCard) like CONCAT('%',LOWER(:search),'%')) ")
	Page<Patient> findPatientBySearch(@Param("pathologies") Collection pathologies, @Param("search")String search, Pageable pageable);

	boolean existsByNhc(String nhc);

	boolean existsByHealthCard(String healthCard);

	boolean existsByDni(String dni);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);
}

