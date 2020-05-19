package es.plexus.hopes.hopesback.repository.utils;

public class QueryConstants {
	public static final String SELECT_PT_FROM_PATIENT_TREATMENT_PT = "select pt from PatientTreatment pt ";

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

}
