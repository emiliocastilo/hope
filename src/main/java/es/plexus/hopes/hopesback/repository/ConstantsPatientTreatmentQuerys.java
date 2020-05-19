package es.plexus.hopes.hopesback.repository;

public class ConstantsPatientTreatmentQuerys {

	public static final String QUERY_DETAIL_TRATMENTS_INFO = 
		"select new es.plexus.hopes.hopesback.controller.model.DetailGraphDTO(" +
			"pac.nhc, pac.healthCard, " +
			"concat(pac.name, ' ', pac.firstSurname, ' ', pac.lastSurname) as patient, " + 
			"ptr.indication, " + 
			"ptd.indication, " +
			"med.actIngredients, " + 
			"hou.value, hou.date, " +
			"hou2.value, hou2.date " +
		") " +
			
		/*"select pac.nhc, pac.healthCard, pac.name, pac.firstSurname, pac.lastSurname, " + 
		"ptr.indication, " + 
		"ptd.indication ," +
		"med.actIngredients, " + 
		"hou.indexType, hou.value, hou.date " +
		"hou2.indexType, hou2.value, hou2.date " +*/
	
		"from PatientTreatment ptr " + 
		"join PatientDiagnose ptd on ptr.patientDiagnose.id = ptd.id " +
		"join Patient pac on ptr.patient.id = pac.id " +
		"join Medicine med on ptr.medicine.id = med.id " +
		"left join HealthOutcome hou on ptr.patient.id = hou.patient.id and hou.indexType = 'PASI' " +
		//Join de prueba
		"left join HealthOutcome hou2 on ptr.patient.id = hou2.patient.id and hou2.indexType = 'DLQI' " +
		
		"where ptr.type = :type " + 
		"and (:indication is null or ptr.indication = :indication) " + 
		"and ptr.active = true " + 
					
		"group by ptr.id, pac.id, ptd.indication, med.actIngredients, " + 
		"hou.indexType, hou.value, hou.date, " +
		"hou2.indexType, hou2.value, hou2.date ";
	
}
