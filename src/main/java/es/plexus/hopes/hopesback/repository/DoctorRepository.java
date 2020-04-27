package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	@Query("select doc from Doctor doc "
			+ " WHERE LOWER(doc.name) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(doc.surname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(doc.phone) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(doc.dni) like CONCAT('%',LOWER(:search),'%') "
			+ " OR CAST(doc.collegeNumber as text) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(doc.user.username) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(doc.user.email) like CONCAT('%',LOWER(:search),'%') ")
	Page<Doctor> findDoctorsBySearch(@Param("search")String search, Pageable pageable);
	
}
