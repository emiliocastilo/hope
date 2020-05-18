package es.plexus.hopes.hopesback.repository;

public class ConstantsPatientTreatmentQuerys {

	public static final String QUERY_PATIENTS_UNDER_TRATMENT = 
		"select new es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO(med.codeAct , med.actIngredients , count(*)) from PatientTreatment ptr " + 
		"join Medicine med on ptr.medicine.id = med.id " + 
		"where ptr.type = :type " + 
		"and (:indication is null or ptr.indication = :indication) " + 
		"and ptr.active = true " + 
		"group by med.codeAct, med.actIngredients ";
	
	public static final String QUERY_INFO_PATIENTS_DOSES = 
		"select new es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO(ptr.regimen , count(*)) from PatientTreatment ptr " + 
		"where ptr.active = true " + 
		"group by ptr.regimen ";
	
	public static final String QUERY_DETAIL_TRATMENTS_INFO = 
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
		
		"where ptr.type = :type " + 
		"and (:indication is null or ptr.indication = :indication) " + 
		"and ptr.active = true " + 
					
		"group by ptr.id, pac.id, ptd.indication, med.actIngredients, " + 
		"hou.indexType, hou.value, hou.date ";
	
}
