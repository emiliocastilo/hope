package es.plexus.hopes.hopesback.repository.utils;

public class QueryConstants {
	public static final String SELECT_PT_FROM_PATIENT_TREATMENT_PT = "select pt from PatientTreatment pt ";
	public static final String SELECT_HO_FROM_HEALTHOUTCOME_HO = "select ho from HealthOutcome ho ";

	public static final String SELECT_PATIENTS_GRAPH_DETAILS_DTO =
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
			"from PatientTreatment ptr " +
				"join Patient pat on ptr.patient.id = pat.id " +
				"join PatientDiagnose pdg on ptr.patientDiagnose.id = pdg.id and ptr.patient.id = pdg.patient.id " +
				"left join Medicine med on ptr.medicine.id = med.id " +
				"left join HealthOutcome hou on pat.id = hou.patient.id ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_NO_TYPE =
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
			"where ptr.active = true " +
			"and (hou.date is null or hou.date = " +
			"(select max(h.date) from HealthOutcome h where h.patient.id = pat.id)) ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
					"where lower(ptr.type) = 'biologico' " +
					"and ptr.active = true " +
					"and (hou.date is null or hou.date = " +
					"(select max(h.date) from HealthOutcome h where h.patient.id = pat.id)) ";

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

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_NO_CHANGES_BIOLOGICAL_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					"where LOWER(pt.type) = 'biologico' " +
					"and LOWER(pt.endCause) <> 'cambio' " +
					"and pt.patient.id not in (select ptr.patient.id from PatientTreatment ptr " +
												"where LOWER(ptr.type) = 'biologico' " +
												"and LOWER(ptr.endCause) = 'cambio') " +
					"group by pt.id, pt.patient";

	public static final String QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where LOWER(pt.type) = LOWER(:type) " +
			"and (:indication is null or pt.indication = :indication)";
	
	public static final String QUERY_PATIENTS_TREAMENT_PER_DOSES =
			QUERY_PATIENTS_GRAPH_DETAILS_NO_TYPE + " and hou.indexType in ('PASI','DLQI') ";
	
	public static final String QUERY_FIND_RESULTS_BY_TYPES = 
			SELECT_HO_FROM_HEALTHOUTCOME_HO + 
			"where ho.indexType = :type ";
	
	public static final String QUERY_FIND_INFO_PATIENTS_DOSES = 
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = true ";
	
	public static final String QUERY_GET_DETAIL_RESULTS_BY_TYPE = 
			QUERY_PATIENTS_GRAPH_DETAILS_NO_TYPE + "and hou.indexType = :indexType ";
	
	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_INDICATIONS =
			QUERY_PATIENTS_GRAPH_DETAILS + "and pdg.indication = :indication ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE9 =
			QUERY_PATIENTS_GRAPH_DETAILS + "and pdg.cie9Desc = :cie9 ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE10 =
			QUERY_PATIENTS_GRAPH_DETAILS + "and pdg.cie10Desc = :cie10 ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_TREATMENT_TYPE =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
			"where " +
				"lower(ptr.type) = lower(:treatmentType) " +
				"and ptr.active = true " +
				"and (hou.date is null or hou.date = " +
				"(select max(h.date) from HealthOutcome h where h.patient.id = pat.id)) ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_END_CAUSE =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
			"where " +
				"ptr.active = false " +
				"and LOWER(ptr.type) = 'biologico' " +
				"and (hou.date is null or hou.date = " +
					"(select max(h.date) from HealthOutcome h where h.patient.id = pat.id)) " +
				"and LOWER(ptr.endCause) = LOWER(:endCause) " +
				"and LOWER(ptr.reason) = LOWER(:reason) " +
				"and ptr.initDate = " +
					"(select max(p.initDate) " +
					"from PatientTreatment p " +
					"where p.patient.id=ptr.patient.id " +
					"and p.active = false)";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_END_CAUSE_IN_LAST_YEARS =
			QUERY_PATIENTS_GRAPH_DETAILS_BY_END_CAUSE
					+ "and ptr.initDate > :initDate ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_PATIENTS_ID =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
					"where " +
					"(hou.date is null or hou.date = " +
					"(select max(h.date) from HealthOutcome h where h.patient.id = ptr.patient.id)) " +
					"and ptr.initDate = " +
					"(select max(p.initDate) " +
					"from PatientTreatment p " +
					"where p.patient.id=ptr.patient.id)" +
					"and ptr.patient.id in (:patientsIds)";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_COMBINED_TREATMENT =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
				"where ptr.active = true " +
					"and (hou.date is null or hou.date = " +
						"(select max(h.date) from HealthOutcome h where h.patient.id = ptr.patient.id)) " +
					"and ptr.initDate = " +
							"(select max(p.initDate) " +
							"from PatientTreatment p " +
							"where p.patient.id=ptr.patient.id " +
							"and p.active = true) " +
					"and ptr.patient in (select pt2.patient from PatientTreatment pt2 " +
										"where pt2.active = true group by pt2.patient having count(*)>=2) " +
					"order by ptr.patient";
	
	// ----------------------- DATOS FARMA-ECONOMICOS ---------------------------------
	public static final String SELECT_FIND_ECONOMIC_RESULTS = 
			"select " +
			"coalesce(sum(dd.amount),0) as total from Dispensation d " + 
			"join DispensationDetail dd on d.id = dd.dispensation.id " +
			"join Medicine me on dd.code = me.codeAct " + 
			"and me.biologic = true ";
	
	public static final String WHERE_FIND_ECONOMIC_RESULTS = 
			"where dd.date between :dateStart and :dateEnd "; 
	
	public static final String QUERY_FIND_RESULTS_ALL_PATIENTS_BY_MONTH = 
			SELECT_FIND_ECONOMIC_RESULTS +
			WHERE_FIND_ECONOMIC_RESULTS;
	
	public static final String QUERY_FIND_RESULTS_PASI_PATIENTS_BY_MONTH = 
			SELECT_FIND_ECONOMIC_RESULTS +
			WHERE_FIND_ECONOMIC_RESULTS +
			" and (select " +
			"sum(cast(ho.value as double)) as total from HealthOutcome ho " + 
			"where ho.date > :date " +
			"and ho.patient.id = :patient )  <=3";
	
	public static final String QUERY_ALL_PATIENTS_HEALHT_OUTCOME =
			"select ho.patient.id from HealthOutcome ho " +
			"group by ho.patient.id";
	
	public static final String QUERY_NUMBER_PATIENTS_MONTH =
			"select dd.nhc from DispensationDetail dd " +
			"where dd.date between :dateStart and :dateEnd " + 
			"group by dd.nhc";
}
