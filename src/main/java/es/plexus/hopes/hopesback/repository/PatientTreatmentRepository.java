package es.plexus.hopes.hopesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;

@Repository
public interface PatientTreatmentRepository extends JpaRepository<PatientTreatment, Long> {

	@Query("select new es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO(med.codeAct , med.actIngredients , count(*)) from PatientTreatment ptr " + 
			"join Medicine med on ptr.medicine.id = med.id " + 
			"where ptr.type = :type " + 
			"and (:indication is null or ptr.indication = :indication) " + 
			"and ptr.active = true " + 
			"group by med.codeAct, med.actIngredients ")
	List<TreatmentInfoDTO> patientsUnderTreatment(@Param("type")String type, @Param("indication")String indication);
	
}

