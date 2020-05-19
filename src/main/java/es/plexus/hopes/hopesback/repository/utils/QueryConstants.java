package es.plexus.hopes.hopesback.repository.utils;

public class QueryConstants {
	public static final String SELECT_PT_FROM_PATIENT_TREATMENT_PT = "select pt from PatientTreatment pt ";
	public static final String SELECT_HO_FROM_HEALTHOUTCOME_HO = "select ho from HealthOutcome ho ";

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
	
	public static final String QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT + 
			"join Medicine me on pt.medicine.id = me.id " +
			"where LOWER(pt.type) = LOWER(:type) " +
			"and (:indication is null or pt.indication = :indication) " +
			"and pt.active = true ";
	
	public static final String QUERY_FIND_RESULTS_BY_TYPES = 
			SELECT_HO_FROM_HEALTHOUTCOME_HO + 
			"where ho.indexType = :type ";
	
	public static final String QUERY_FIND_RESULTS_BY_TYPES_AND_MAX_DATE = 
			SELECT_HO_FROM_HEALTHOUTCOME_HO + 
			"where ho.indexType = :type " + 
			"and ho.date =" + 
			"(" + 
			"	select max(ho2.date) as maxDate " + 
			"	from HealthOutcome ho2 " + 
			"	where ho2.patient.id = ho.patient.id " + 
			"	group by ho2.patient.id " + 
			") ";

	public static final String QUERY_FIND_INFO_PATIENTS_DOSES = 
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = true ";
	
}
