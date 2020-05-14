package es.plexus.hopes.hopesback.repository.utils;

public class QueryConstants {
	public static final String QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION =
			"select med.codeAct , med.actIngredients , count(*) from PatientTreatment ptr " +
			"join Medicine med on ptr.medicine.id = med.id " +
			"where ptr.type = :type " +
			"and (:indication is null or ptr.indication = :indication) " +
			"and ptr.active = true " +
			"group by med.codeAct, med.actIngredients ";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_TREATMENT =
			"select pt.type, count(*) as numberPatients from PatientTreatment pt " +
			"where pt.active = true " +
			"and pt.patient in (" +
					"select pt2.patient from PatientTreatment pt2 " +
					"where pt2.active = true group by pt2.patient having count(*)<= 1" +
					") " +
			"group by pt.type";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_WITHOUT_TREATMENT =
			"select 'Sin tratamiento' as type, count(*) as numberPatients from PatientTreatment pt "+
			"where pt.active = false and pt.patient " +
			"not in (select pt2.patient from PatientTreatment pt2 where pt2.active =true)";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_COMBINED_TREATMENT =
			"select pt.type, count(*) as numberPatients from PatientTreatment pt " +
			"where pt.active = true " +
			"and pt.patient in (select pt2.patient from PatientTreatment pt2 " +
			"where pt.active = true group by pt2.patient having count(*)>=2) " +
			"group by pt.type";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE =
			"select pt.reason, count(*) as numberPatients from PatientTreatment pt " +
			"join PatientTreatment pt2 on pt2.id = pt.id and pt2.active = false " +
			"and pt2.type = 'BIOLOGICO' and pt2.endCause = :endCause "+
			"where pt.active = false " +
			"and pt.type = 'BIOLOGICO' " +
			"and pt.endCause = :endCause " +
			"and pt.initDate > current_timestamp()  " +
			"group by pt.reason";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE_IN_LAST_5_YEARS =
			"select pt.reason, count(*) as numberPatients from PatientTreatment pt " +
			"where pt.active = false " +
			"and pt.type = 'BIOLOGICO' " +
			"and pt.endCause = :endCause " +
			"and pt.initDate > :initDate " +
			"group by pt.reason";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_NUMBER_CHANGES_OF_BIOLOGICAL_TREATMENT =
			"select pt.patient, count(*) as numberPatients from PatientTreatment pt " +
			"join pt.patient pac " +
			"where pt.type = 'BIOLOGICO' " +
			"and pt.endCause = 'CAMBIO' " +
			"group by pt.patient, pac.id";
}
