package es.plexus.hopes.hopesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.repository.model.Patient;

@Repository
public interface HealthOutcomeRepository extends JpaRepository<Patient, Long> {

	@Query("select new es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO(hou.result, count(*)) from HealthOutcome hou " + 
			"where hou.indexType = :type " + 
			"and hou.date = " +
			"(" +
				"select max(hou2.date) as maxDate " +
				"from HealthOutcome hou2 " + 
				"where hou2.patient.id = hou.patient.id " + 
				"group by hou2.patient.id" +
			") " + 
			"group by hou.patient.id, hou.result ")
	List<HealthOutcomeDTO> getHealthOutcomesByType(@Param("type")String type);
	
}

