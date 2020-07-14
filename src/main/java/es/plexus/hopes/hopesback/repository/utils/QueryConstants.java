package es.plexus.hopes.hopesback.repository.utils;

public class QueryConstants {

	public static final String SELECT_PT_FROM_PATIENT_TREATMENT_PT = "select pt from PatientTreatment pt ";
	public static final String SELECT_HO_FROM_HEALTHOUTCOME_HO = "select ho from HealthOutcome ho ";
	public static final String FILTER_DATE_OF_HEALTHOUTCOME_BY_PATIENT = "and (hou.date is null or hou.date = " +
			"(select max(h.date) from HealthOutcome h where h.patient.id = pat.id)) ";

	public static final String SELECT_PATIENTS_GRAPH_DETAILS_DTO =
			"select new es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO(" +
				"pat.id , " +
				"pat.nhc , " +
				"pat.healthCard, " +
				"concat(pat.name, ' ', pat.firstSurname, ' ', pat.lastSurname) as fullName, " +
				"ind.description , " +
				"concat(c09.code, ' ', c09.description), " +
				"concat(c10.code, ' ', c10.description), " +
				"med.actIngredients, " +
				"case hou.indexType when 'PASI' then hou.result else '' end, " +
				"case hou.indexType when 'PASI' then hou.date else null end , " +
				"case hou.indexType when 'DLQI' then hou.result else '' end, " +
				"case hou.indexType when 'DLQI' then hou.date else null end) " +
			"from Patient pat " +
				"inner join PatientDiagnose pdg on pdg.patient.id = pat.id " +
				"inner join PatientTreatment ptr on ptr.patientDiagnose.id = pdg.id " +
				"left join Medicine med on ptr.medicine.id = med.id " +
				"left join HealthOutcome hou on pat.id = hou.patient.id " +
				"left join Cie9 c09 on pdg.cieCode = c09.code " +
				"left join Cie10 c10 on pdg.cieCode = c10.code " +
				"left join Indication ind on pdg.indication.id = ind.id ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_NO_TYPE =
			"select new es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO(" +
			"pat.id , " +
			"pat.nhc , " +
			"pat.healthCard, " +
			"concat(pat.name, ' ', pat.firstSurname, ' ', pat.lastSurname) as fullName, " +
			"ind.description , " +
			"concat(c09.code, ' ', c09.description), " +
			"concat(c10.code, ' ', c10.description), " +
			"med.actIngredients, " +
			"case hou.indexType when 'PASI' then hou.result else '' end, " +
			"case hou.indexType when 'PASI' then hou.date else null end , " +
			"case hou.indexType when 'DLQI' then hou.result else '' end, " +
			"case hou.indexType when 'DLQI' then hou.date else null end) " +
			"from Patient pat " +
			"join PatientDiagnose pdg on pdg.patient.id = pat.id " +
			"join PatientTreatment ptr on ptr.patientDiagnose.id = pdg.id " +
			"left join Medicine med on ptr.medicine.id = med.id " +
			"left join HealthOutcome hou on pat.id = hou.patient.id " +
			"left join Cie9 c09 on pdg.cieCode = c09.code " +
			"left join Cie10 c10 on pdg.cieCode = c10.code " +
			"left join Indication ind on pdg.indication.id = ind.id " +
			"where ptr.active = true " +
					FILTER_DATE_OF_HEALTHOUTCOME_BY_PATIENT;

	public static final String QUERY_PATIENTS_GRAPH_DETAILS =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
					"where lower(ptr.type) = 'biologico' " +
					"and ptr.active = true " +
					FILTER_DATE_OF_HEALTHOUTCOME_BY_PATIENT;

	public static final String WHERE_PT_ACTIVE_TRUE = "where pt.active = true ";
	public static final String QUERY_PATIENTS_DIAGNOSES_BY_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					WHERE_PT_ACTIVE_TRUE +
			"and pt.patientDiagnose in (" +
					"select pt2.patientDiagnose from PatientTreatment pt2 " +
					"where pt2.active = true group by pt2.patientDiagnose having count(*)<= 1" +
					") " +
			"group by pt.type, pt";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_WITHOUT_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = false and pt.patientDiagnose " +
			"not in (select pt2.patientDiagnose from PatientTreatment pt2 where pt2.active =true)";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_COMBINED_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					WHERE_PT_ACTIVE_TRUE +
			"and pt.patientDiagnose in (select pt2.patientDiagnose from PatientTreatment pt2 " +
			"where pt2.active = true group by pt2.patientDiagnose having count(*)>=2) " +
			"order by pt.patientDiagnose";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = false " +
			"and LOWER(pt.type) = 'biologico' " +
			"and LOWER(pt.endCause) = coalesce(LOWER(:endCause), 'Otras') " +
			"group by pt.id, pt.patientDiagnose " +
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
			"group by pt.id, pt.patientDiagnose";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_NO_CHANGES_BIOLOGICAL_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					"where LOWER(pt.type) = 'biologico' " +
					"and LOWER(pt.endCause) <> 'cambio' " +
					"and pt.patientDiagnose.id not in (select ptr.patientDiagnose.id from PatientTreatment ptr " +
												"where LOWER(ptr.type) = 'biologico' " +
												"and LOWER(ptr.endCause) = 'cambio') " +
					"group by pt.id, pt.patientDiagnose";

	public static final String QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"join PatientDiagnose pdg on pdg.id = pt.patientDiagnose.id " +
			"where LOWER(pt.type) = LOWER(:type) " +
			"and (:indication is null or pdg.indication = :indication)";
	
	public static final String QUERY_PATIENTS_TREAMENT_PER_DOSES =
			QUERY_PATIENTS_GRAPH_DETAILS_NO_TYPE + " and hou.indexType in ('PASI','DLQI') ";
	
	public static final String QUERY_FIND_RESULTS_BY_TYPES = 
			SELECT_HO_FROM_HEALTHOUTCOME_HO + 
			"where ho.indexType = :type ";
	
	public static final String QUERY_FIND_INFO_PATIENTS_DOSES = 
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					WHERE_PT_ACTIVE_TRUE;
	
	public static final String QUERY_GET_DETAIL_RESULTS_BY_TYPE = 
			QUERY_PATIENTS_GRAPH_DETAILS_NO_TYPE + "and hou.indexType = :indexType ";
	
	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_INDICATIONS =
			QUERY_PATIENTS_GRAPH_DETAILS + "and ind.description = :indication ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE9 =
			QUERY_PATIENTS_GRAPH_DETAILS + "and c09.description = :cie9 ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE10 =
			QUERY_PATIENTS_GRAPH_DETAILS + "and c10.description = :cie10 ";

	public static final String WHERE_CLAUSULE = "where ";
	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_TREATMENT_TYPE =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
					WHERE_CLAUSULE +
				"lower(ptr.type) = lower(:treatmentType) " +
				"and ptr.active = true " +
					FILTER_DATE_OF_HEALTHOUTCOME_BY_PATIENT;

	public static final String AND_PTR_INIT_DATE = "and ptr.initDate = ";
	public static final String SELECT_MAX_P_INIT_DATE = "(select max(p.initDate) ";
	public static final String FROM_PATIENT_TREATMENT_P = "from PatientTreatment p ";
	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_END_CAUSE =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
					WHERE_CLAUSULE +
				"ptr.active = false " +
				"and LOWER(ptr.type) = 'biologico' " +
					FILTER_DATE_OF_HEALTHOUTCOME_BY_PATIENT +
				"and LOWER(ptr.endCause) = LOWER(:endCause) " +
				"and LOWER(ptr.reason) = LOWER(:reason) " +
					AND_PTR_INIT_DATE +
					SELECT_MAX_P_INIT_DATE +
					FROM_PATIENT_TREATMENT_P +
					"where p.patientDiagnose.id=ptr.patientDiagnose.id " +
					"and p.active = false)";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_END_CAUSE_IN_LAST_YEARS =
			QUERY_PATIENTS_GRAPH_DETAILS_BY_END_CAUSE
					+ "and ptr.initDate > :initDate ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_PATIENTS_ID =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
					WHERE_CLAUSULE +
					"(hou.date is null or hou.date = " +
					"(select max(h.date) from HealthOutcome h where h.patient.id = pat.id)) " +
					AND_PTR_INIT_DATE +
					SELECT_MAX_P_INIT_DATE +
					FROM_PATIENT_TREATMENT_P +
					"where p.patientDiagnose.id=pdg.id)" +
					"and pat.id in (:patientsIds)";


	public static final String QUERY_PATIENTS_GRAPH_DETAILS_BY_COMBINED_TREATMENT =
			SELECT_PATIENTS_GRAPH_DETAILS_DTO +
					"where ptr.active = true " +
					FILTER_DATE_OF_HEALTHOUTCOME_BY_PATIENT +
					AND_PTR_INIT_DATE +
					SELECT_MAX_P_INIT_DATE +
					FROM_PATIENT_TREATMENT_P +
					"where p.patientDiagnose.id=pdg.id " +
					"and p.active = true) " +
					"and ptr.patientDiagnose in (select pt2.patientDiagnose from PatientTreatment pt2 " +
					"where pt2.active = true group by pt2.patientDiagnose having count(*)>=2) " +
					"order by ptr.patientDiagnose";
	
	// ----------------------- DATOS FARMA-ECONOMICOS ---------------------------------
	public static final String SELECT_FIND_BIO_ECONOMIC_RESULTS = 
			"select " +
			"coalesce(sum(dd.amount),0) as total from Dispensation d " + 
			"join DispensationDetail dd on d.id = dd.dispensation.id " +
			"join Medicine me on dd.code = me.codeAct " + 
			"and me.biologic = true ";
	
	public static final String WHERE_FIND_BIO_ECONOMIC_RESULTS = 
			"where dd.date between :dateStart and :dateEnd " +
			"and (:code is null or dd.code = :code)"; 


	public static final String QUERY_FIND_RESULTS_ALL_PATIENTS_BY_MONTH = 
			SELECT_FIND_BIO_ECONOMIC_RESULTS +
			WHERE_FIND_BIO_ECONOMIC_RESULTS;
	
	public static final String QUERY_FIND_RESULTS_PASI_PATIENTS_BY_MONTH = 
			SELECT_FIND_BIO_ECONOMIC_RESULTS +
			WHERE_FIND_BIO_ECONOMIC_RESULTS +
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

	public static final String QUERY_VALUES_HEALHT_OUTCOME_BY_INDEX_TYPE_PATIENT_ID =
			"select hou from HealthOutcome hou " +
			"where hou.indexType = :indexType and hou.patient.id = :patId " +
			"order by hou.date asc";

	// PATHOLOGY DASHBOARD: PATIENT DIAGNOSES - PATIENTS GROUP BY INDICATION

	public static final String QUERY_PATIENTS_DATA_BY_PATIENTS_ID =
			"select pda " +
			"from PatientData pda " +
			"where pda.patient.id in (:patientsIds) " +
				"and pda.date = " +
					"(select max(pda2.date) " +
						"from PatientData pda2 " +
						"where pda.patient.id = pda2.patient.id)";

	public static final String SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG = "select pdg from PatientDiagnose pdg ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_GROUP_BY_INDICATION =
			SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG +
					"join Indication c on c.id = pdg.indication.id ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_GROUP_BY_CIE9 =
			SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG +
			"join Cie9 c on c.code = pdg.cieCode ";

	public static final String QUERY_PATIENTS_GRAPH_DETAILS_GROUP_BY_CIE10 =
			SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG +
					"join Cie10 c on c.code = pdg.cieCode";

	public static final String ORDER_BY_INIT_DATE_IN_PATIENT_TREATMENT = "order by pt.initDate ";

	public static final String QUERY_ACTUAL_TREATMENTS_BY_PATIENT_ID =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
				"join PatientDiagnose pd on pt.patientDiagnose.id = pd.id " +
			WHERE_PT_ACTIVE_TRUE +
			"and pd.patient.id = :patId " +
			ORDER_BY_INIT_DATE_IN_PATIENT_TREATMENT;

	public static final String QUERY_FIND_DISPENSATION_DETAILS_BY_PATIENT_ID =
			"select dd " +
			"from DispensationDetail dd " +
				"join Patient p on p.nhc = dd.nhc " +
				"join PatientDiagnose pd on pd.patient.id = p.id " +
				"join PatientTreatment pt on pt.patientDiagnose.id = pd.id " +
				"join Medicine m on m.id = pt.medicine.id and m.nationalCode = CAST(dd.nationalCode as text) " +
			WHERE_PT_ACTIVE_TRUE +
				"and p.id = :patId ";

}
