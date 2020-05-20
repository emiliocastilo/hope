package es.plexus.hopes.hopesback.repository.utils;

public class QueryConstants {
	public static final String SELECT_PT_FROM_PATIENT_TREATMENT_PT = "select pt from PatientTreatment pt ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS =
			"select new es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO(" +
			"pat.id , " +
			"pat.nhc , " +
			"pat.healthCard, " +
			"concat(pat.name, ' ', pat.firstSurname, ' ', pat.lastSurname) as fullName, " +
			"pdg.indication , " +
			"concat(pdg.cie9Code, ' ', pdg.cie9Desc), " +
			"concat(pdg.cie10Code, ' ', pdg.cie10Desc), " +
			"med.actIngredients, " +
			"case hou.indexType when 'PASI' then hou.result else '' end, " +
			"case hou.indexType when 'PASI' then hou.date else null end , " +
			"case hou.indexType when 'DLQI' then hou.result else '' end, " +
			"case hou.indexType when 'DLQI' then hou.date else null end) " +
			"from Patient pat " +
			"join PatientTreatment ptr on ptr.patient.id = pat.id " +
			"join PatientDiagnose pdg on ptr.patientDiagnose.id = pdg.id and ptr.patient.id = pdg.patient.id " +
			"left join Medicine med on ptr.medicine.id = med.id " +
			"left join HealthOutcome hou on pat.id = hou.patient.id " +
			"where lower(ptr.type) = 'biologico' " +
			"and ptr.active = true " +
			"and (hou.date = " +
			"(select max(h.date) as maxDate  from HealthOutcome h where h.patient.id = pat.id)) ";

	public static final String QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION =
			"select med.codeAct , med.actIngredients , count(*) from PatientTreatment ptr " +
			"join Medicine med on ptr.medicine.id = med.id " +
			"where ptr.type = :type " +
			"and (:indication is null or ptr.indication = :indication) " +
			"and ptr.active = true " +
			"group by med.codeAct, med.actIngredients ";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = true " +
			"and pt.patient in (" +
					"select pt2.patient from PatientTreatment pt2 " +
					"where pt2.active = true group by pt2.patient having count(*)<= 1" +
					") " +
			"group by pt.type, pt";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_WITHOUT_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = false and pt.patient " +
			"not in (select pt2.patient from PatientTreatment pt2 where pt2.active =true)";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_COMBINED_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = true " +
			"and pt.patient in (select pt2.patient from PatientTreatment pt2 " +
			"where pt2.active = true group by pt2.patient having count(*)>=2) " +
			"order by pt.patient";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = false " +
			"and LOWER(pt.type) = 'biologico' " +
			"and LOWER(pt.endCause) = coalesce(LOWER(:endCause), 'Otras') " +
			"group by pt.id, pt.patient " +
			"order by pt.initDate desc";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE_IN_LAST_5_YEARS =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = false " +
			"and LOWER(pt.type) = 'biologico' " +
			"and LOWER(pt.endCause) = coalesce(LOWER(:endCause), 'otras') " +
			"and pt.initDate > :initDate ";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_NUMBER_CHANGES_OF_BIOLOGICAL_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where LOWER(pt.type) = 'biologico' " +
			"and LOWER(pt.endCause) = 'cambio' " +
			"group by pt.id, pt.patient";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_INDICATIONS =
			QUERY_PATIENTS_GRAPH_DETAILS + 	"and pdg.indication = :indication ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE9 =
			QUERY_PATIENTS_GRAPH_DETAILS +  "and pdg.cie9Desc = :cie9 ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE10 =
			QUERY_PATIENTS_GRAPH_DETAILS + 	"and pdg.cie10Desc = :cie10 ";

}
