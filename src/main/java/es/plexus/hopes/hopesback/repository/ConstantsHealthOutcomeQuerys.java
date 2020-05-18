package es.plexus.hopes.hopesback.repository;

public class ConstantsHealthOutcomeQuerys {

	public static final String QUERY_FIND_RESULTS_BY_TYPES = 
			"select new es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO(hou.result, count(*)) from HealthOutcome hou " + 
			"where hou.indexType = :type " + 
			"and hou.date =" + 
			"(" + 
			"	select max(hou2.date) as maxDate " + 
			"	from HealthOutcome hou2 " + 
			"	where hou2.patient.id = hou.patient.id " + 
			"	group by hou2.patient.id " + 
			") " + 
			"group by hou.patient.id, hou.result";
	
	public static final String QUERY_DETAIL_RESULTS_INFO = 
			"select pac.nhc, pac.healthCard, pac.name, pac.firstSurname, pac.lastSurname, " + 
			"ptr.indication, " + 
			"ptd.indication ," +
			"med.actIngredients, " + 
			"hou.indexType, hou.value, hou.date " +
	
		"from PatientTreatment ptr " + 
		"join PatientDiagnose ptd on ptr.patientDiagnose.id = ptd.id " +
		"join Patient pac on ptr.patient.id = pac.id " +
		"join Medicine med on ptr.medicine.id = med.id " +
		"join HealthOutcome hou on ptr.patient.id = hou.patient.id " +
		
		"where hou.indexType = :type " + 
		"and ptr.active = true " + 
		"and hou.date = " +
		"(" +
			"select max(hou2.date) as maxDate " +
			"from HealthOutcome hou2 " + 
			"where hou2.patient.id = hou.patient.id " + 
			"group by hou2.patient.id" +
		") " +			
		
		"group by ptr.id, pac.id, ptd.indication, med.actIngredients, " + 
		"hou.indexType, hou.value, hou.date ";
	
}
