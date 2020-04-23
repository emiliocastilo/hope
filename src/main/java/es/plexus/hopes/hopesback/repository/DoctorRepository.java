package es.plexus.hopes.hopesback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.repository.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {
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
