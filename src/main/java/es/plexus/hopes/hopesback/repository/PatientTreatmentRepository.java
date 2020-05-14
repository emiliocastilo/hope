package es.plexus.hopes.hopesback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO;
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
	
	@Query("select new es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO(ptr.regimen , count(*)) from PatientTreatment ptr " + 
			"where ptr.active = true " + 
			"group by ptr.regimen ")
	List<PatientDosesInfoDTO> infoPatientsDoses();
	

	@Query(/*"select new es.plexus.hopes.hopesback.controller.model.DetailGraphDTO" +
			"(" +
				"" +
			") " + */
			"select ptd.indication " + 
			"from PatientTreatment ptr " + 
			"join PatientDiagnose ptd on ptr.patientDiagnose.id = ptd.id " +
			"join Patient pac on ptr.patient.id = pac.id " +
			"join Medicine med on ptr.medicine.id = med.id " +
			"join HealthOutcome hou on pac.id = hou.patient.id " +
			
			"where ptr.type = :type " + 
			"and (:indication is null or ptr.indication = :indication) " + 
			"and ptr.active = true " + 
			
			"and hou.date = " +
			"(" +
				"select max(hou2.date) as maxDate " +
				"from HealthOutcome hou2 " + 
				"where hou2.patient.id = hou.patient.id " + 
				"group by hou2.patient.id" +
			") " + 
			
			"group by ptr.id, pac.id, ptd.indication, med.actIngredients, " + 
			"hou.indexType, hou.value, hou.date ")
	Page<String> detailsDrapths(@Param("type")String type, @Param("indication")String indication, Pageable pageable);
	//Page<TreatmentInfoDTO> detailsDrapths(@Param("type")String type, @Param("indication")String indication, Pageable pageable);
}

