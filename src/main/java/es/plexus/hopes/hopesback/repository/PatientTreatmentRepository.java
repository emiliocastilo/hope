package es.plexus.hopes.hopesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.repository.model.Patient;

@Repository
public interface PatientTreatmentRepository extends JpaRepository<Patient, Long> {

	@Query("select med.codeAct , med.actIngredients , count(*) from PatientTreatment ptr " + 
			"join Medicine med on ptr.medicine.id = med.id " + 
			"where ptr.type = :type " + 
			"and (:indication is null or ptr.indication = :indication) " + 
			"and ptr.active = 'S' " + 
			"group by med.codeAct, med.actIngredients ")
	List<Object[]> patientsUnderTreatment(@Param("type")String type, @Param("indication")String indication);
	
}

