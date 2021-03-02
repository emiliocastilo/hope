package es.plexus.hopes.hopesback.repository.utils;

public class QueryConstants {

	public static final String SELECT_PT_FROM_PATIENT_TREATMENT_PT = "select pt from PatientTreatment pt ";
	public static final String SELECT_HO_FROM_HEALTHOUTCOME_HO = "select ho from HealthOutcome ho ";

	public static final String WHERE_PT_ACTIVE_TRUE = "where pt.active = true ";
	public static final String AND_PT_MEDICINE_AND_REGIME = " and upper(pt.type) = 'BIOLOGICO' ";

	public static final String FILTER_PTL_ACTIVE_TRUE = " and ptl.active = true ";
	public static final String FILTER_PTL_TYPE = "and LOWER(ptl.type) = LOWER(:type) ";

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
			" join PatientTreatmentLine ptl on  ptl.patientTreatment = pt.id " +
			"group by pt.id, pt.patientDiagnose " +
			"order by pt.initDate desc ";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE_IN_LAST_5_YEARS =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
			"where pt.active = false " +
			"and LOWER(pt.type) = 'biologico' " +
			"and LOWER(pt.endCause) = coalesce(LOWER(:endCause), 'otras') " +
			"and pt.initDate > :initDate ";

	public static final String QUERY_PATIENTS_DIAGNOSES_BY_NUMBER_CHANGES_OF_BIOLOGICAL_TREATMENT =
			"select ptr from  PatientDiagnose pdg " +
					" join PatientTreatment ptr " +
					" on pdg.id = ptr.patientDiagnose.id " +
					"  join PatientTreatmentLine ptl " +
					" on ptr.id = ptl.patientTreatment" +
					" where ptl.active = true and (ptl.deleted = false or ptl.deleted is null)";



	public static final String QUERY_PATIENTS_DIAGNOSES_BY_NO_CHANGES_BIOLOGICAL_TREATMENT =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					"where LOWER(pt.type) = 'biologico' " +
					"and LOWER(pt.endCause) = 'cambio' and pt.reason is null " +
					"group by pt.id, pt.patientDiagnose";

	public static final String QUERY_FIND_RESULTS_BY_TYPES =
			"select new es.plexus.hopes.hopesback.repository.model.HealthOutcome(max(ho.id) , " +
			"ho.patient, ho.indexType, max(ho.value), ho.result, max(ho.date )) from HealthOutcome ho " +
			"where ho.indexType = :type " +
			"group by ho.patient,  ho.indexType, ho.result";

	public static final String QUERY_FIND_INFO_PATIENTS_DOSES =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					WHERE_PT_ACTIVE_TRUE;

	public static final String QUERY_FIND_INFO_PATIENTS_DOSES_AND_MEDICINES =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					WHERE_PT_ACTIVE_TRUE + AND_PT_MEDICINE_AND_REGIME;

	public static final String WHERE_CLAUSULE = "where ";


	// ----------------------- DATOS FARMA-ECONOMICOS ---------------------------------
	public static final String SELECT_FIND_BIO_ECONOMIC_RESULTS = 
			"select " +
			"coalesce(sum(dd.amount),0) as total from Dispensation d " + 
			"join DispensationDetail dd on d.id = dd.dispensation.id " +
			"join Medicine me on dd.code = me.codeAtc " +
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
			"select ho.patient from HealthOutcome ho ";
	
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

	public static final String QUERY_PATIENTS_DIAGNOSE_BY_INDICATION =
			SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG +
					"join Indication c on c.id = pdg.indication.id ";
	public static final String QUERY_PATIENTS_DIAGNOSE_GROUP_BY_CIE9 =
			SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG +
			"join Cie9 c on c.code = pdg.cieCode ";

	public static final String QUERY_PATIENTS_DIAGNOSE_BY_CIE10 =
			SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG +
					"join Cie10 c on c.code = pdg.cieCode";

	public static final String QUERY_PATIENTS_DIAGNOSE_BY_PATIENT_ID =
			SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG +
					WHERE_CLAUSULE + " pdg.patient.id = :patientId";

	public static final String QUERY_PATIENTS_DIAGNOSE_BY_PATIENT_ID_AND_PATHOLOGY_ID =
			SELECT_PDG_FROM_PATIENT_DIAGNOSE_PDG +
					WHERE_CLAUSULE + " pdg.patient.id = :patientId"
			+ " and pdg.indication.pathologyId = :pathologyId";

	public static final String SELECT_PATIENT = "select pat " +
			"from Patient pat ";

	public static final String SELECT_PATIENT_TREATMENT_FROM_PATIENT = "select ptr " +
			"from Patient pat ";

	public static final String JOIN_FETCH_PATIENT_DIAGNOSE_PDG_ON_PDG_PATIENT_ID_PAT_ID = "join fetch PatientDiagnose pdg on pdg.patient.id = pat.id ";
	public static final String SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE = SELECT_PATIENT +
			JOIN_FETCH_PATIENT_DIAGNOSE_PDG_ON_PDG_PATIENT_ID_PAT_ID;
	public static final String SELECT_PATIENT_TREATMENT_JOIN_PATIENT_DIAGNOSE = SELECT_PATIENT_TREATMENT_FROM_PATIENT +
			JOIN_FETCH_PATIENT_DIAGNOSE_PDG_ON_PDG_PATIENT_ID_PAT_ID;

	public static final String QUERY_PATIENTS_BY_INDICATIONS =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
			"where UPPER(pdg.indication.code) = UPPER(:indication) ";

	public static final String QUERY_PATIENTS_BY_CIE_9 =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
			"join fetch Cie9 c09 on pdg.cieCode = c09.code " +
			"where c09.description = :cie9 ";

	public static final String QUERY_PATIENTS_BY_CIE_10 =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
			"join fetch Cie10 c10 on pdg.cieCode= c10.code " +
			"where c10.description = :cie10 ";

	public static final String JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE =
			"join fetch PatientTreatment ptr on ptr.patientDiagnose.id = pdg.id ";
	public static final String JOIN_FETCH_PATIENT_TREATMENT_PATIENT_TREATMENT_LINES =
			" join fetch PatientTreatmentLine ptl on ptl.patientTreatment = ptr.id ";

	public static final String PTR_ACTIVE_AND_PTR_TYPE = " ptr.active = true and " +
			" lower(ptr.type) = lower(:treatmentType) ";

	public static final String SUB_SELECT_PT_DIAGNOSE_IN_PTR_ACTIVE_AND_DIAGNOSE_HAVING_COUNT =
			"( select pt2.patientDiagnose from PatientTreatment pt2 " +
			"where pt2.active = true group by pt2.patientDiagnose having count(*)  ";

	public static final String AND_PTR_PATIENT_DIAGNOSE_IN = "and ptr.patientDiagnose in ";
	public static final String QUERY_PATIENTS_BY_TREATMENT =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					WHERE_CLAUSULE +
					PTR_ACTIVE_AND_PTR_TYPE;

	public static final String QUERY_PATIENTS_BY_COMBINED_TREATMENT =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					WHERE_CLAUSULE +
					"ptr.active = true "+
					AND_PTR_PATIENT_DIAGNOSE_IN +
					SUB_SELECT_PT_DIAGNOSE_IN_PTR_ACTIVE_AND_DIAGNOSE_HAVING_COUNT +
					" > 1) ";

	public static final String FILTER_PTR_ACTIVE_FALSE = "ptr.active = false ";
	public static final String QUERY_PATIENTS_BY_NO_TREATMENT =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					WHERE_CLAUSULE +
					FILTER_PTR_ACTIVE_FALSE + " and ptr.patientDiagnose " +
			" not in (select pt2.patientDiagnose from PatientTreatment pt2 where pt2.active =true) ";

	public static final String FILTER_ACTIVE_FALSE_AND_END_CAUSE_AND_REASON =
			FILTER_PTR_ACTIVE_FALSE +
			"and LOWER(ptr.type) = 'biologico' " +
			"and LOWER(ptr.endCause) = LOWER(:endCause) " +
			"and LOWER(ptr.reason) = LOWER(:reason) ";

	public static final String ORDER_BY_PTR_FINAL_DATE_DESC= "order by ptr.finalDate";

	public static final String QUERY_PATIENTS_BY_END_CAUSE =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					WHERE_CLAUSULE +
					FILTER_ACTIVE_FALSE_AND_END_CAUSE_AND_REASON +
					ORDER_BY_PTR_FINAL_DATE_DESC;

	public static final String QUERY_PATIENTS_BY_END_CAUSE_IN_LAST_YEARS =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					WHERE_CLAUSULE +
					FILTER_ACTIVE_FALSE_AND_END_CAUSE_AND_REASON +
					"and ptr.initDate > :initDate ";

	public static final String QUERY_PATIENTS_BY_PATIENTS_ID =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
			JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
			WHERE_CLAUSULE +
					"pat.id in (:patientsIds)";

	public static final String QUERY_FIND_PATIENTS_BY_IDS = SELECT_PATIENT + WHERE_CLAUSULE + "pat.id in (:patientsIds)";

	public static final String FILTER_PTR_ACTIVE_TRUE = "ptr.active = true ";
	public static final String QUERY_PATIENTS_BY_COMBINED_TREATMENTS =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					WHERE_CLAUSULE +
					FILTER_PTR_ACTIVE_TRUE +
					"and LOWER(ptr.type) in (:treatments ) " +
					AND_PTR_PATIENT_DIAGNOSE_IN +
					SUB_SELECT_PT_DIAGNOSE_IN_PTR_ACTIVE_AND_DIAGNOSE_HAVING_COUNT +
					" > :numberTreatments) ";

	public static final String JOIN_FETCH_HEALTH_OUTCOME_HOU_PAT_ID_HOU_PATIENT_ID = "join fetch HealthOutcome hou on pat.id = hou.patient.id ";

	public static final String QUERY_GET_DETAIL_RESULTS_BY_TYPE =
			SELECT_PATIENT +
			JOIN_FETCH_HEALTH_OUTCOME_HOU_PAT_ID_HOU_PATIENT_ID +
			"where hou.indexType = :indexType and hou.result = :result";

	public static final String QUERY_PATIENTS_TREAMENT_PER_DOSES =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					WHERE_CLAUSULE +
					FILTER_PTR_ACTIVE_TRUE +
				"and LOWER(ptr.regimen) = LOWER(:regimen) ";

	public static final String JOIN_FETCH_INDICATION_IND_ON_IND_ID_PDG_INDICATION_ID = "join fetch Indication ind on ind.id = pdg.indication.id ";
	public static final String JOIN_MEDICINES_MED_ON_MED_MED_ID_PTR_MED_ID = " join fetch Medicine med on med.id  = ptr.medicine.id ";
	public static final String FILTER_INDICATION_DESCRIPTION = "and LOWER(ind.description) = LOWER(:indication) ";
	public static final String FILTER_MEDICINE_ACT_INGREDIENTS = " and translate(LOWER(med.actIngredients),'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ','aeiouAEIOUaeiouAEIOU') = translate(LOWER(:medicine),'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ','aeiouAEIOUaeiouAEIOU')";
	public static final String FILTER_PTR_TYPE = " and LOWER(ptr.type) = LOWER(:type) ";
	public static final String QUERY_PATIENTS_BY_TREATMENT_TYPE_AND_INDICATION =
			SELECT_PATIENT_JOIN_PATIENT_DIAGNOSE +
			JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE ;

	public static final String QUERY_PATIENTS_TREATMENTS_BY_TREATMENT_TYPE_AND_INDICATION =
			SELECT_PATIENT_TREATMENT_JOIN_PATIENT_DIAGNOSE +
			JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_TREATMENT_LINES +

					JOIN_FETCH_INDICATION_IND_ON_IND_ID_PDG_INDICATION_ID +
			WHERE_CLAUSULE +
			FILTER_PTR_ACTIVE_TRUE +
			FILTER_PTL_TYPE +
			FILTER_PTL_ACTIVE_TRUE +
			" and LOWER(ind.description) = LOWER(:indication) ";


	public static final String QUERY_PATIENTS_TREATMENTS_BY_TREATMENT_TYPE =
			SELECT_PATIENT_TREATMENT_JOIN_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_DIAGNOSE +
					JOIN_FETCH_PATIENT_TREATMENT_PATIENT_TREATMENT_LINES +
					WHERE_CLAUSULE +
					FILTER_PTR_ACTIVE_TRUE +
					FILTER_PTL_ACTIVE_TRUE +
					FILTER_PTL_TYPE;

	public static final String ORDER_BY_INIT_DATE_IN_PATIENT_TREATMENT = "order by pt.initDate ";


	public static final String QUERY_ACTUAL_TREATMENTS_BY_PATIENT_ID =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					"join PatientDiagnose pd on pt.patientDiagnose.id = pd.id " +
					WHERE_PT_ACTIVE_TRUE +
					"and pd.patient.id = :patId " +
					ORDER_BY_INIT_DATE_IN_PATIENT_TREATMENT;

	public static final String QUERY_ALL_BIOLOGICAL_TREATMENTS_BY_PATIENT_ID =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					"join PatientDiagnose pd on pt.patientDiagnose.id = pd.id " +
					WHERE_CLAUSULE +
					" pd.patient.id = :patId " +
					"and LOWER(pt.type) = 'biologico' " +
					ORDER_BY_INIT_DATE_IN_PATIENT_TREATMENT;

	public static final String QUERY_ALL_FAME_DERMA_TREATMENTS_BY_PATIENT_ID =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					"join PatientDiagnose pd on pt.patientDiagnose.id = pd.id " +
					"join Indication i on pd.indication.id = i.id " +
					"join Pathology p on i.pathologyId = p.id " +
					WHERE_CLAUSULE +
					" pd.patient.id = :patId " +
					"and LOWER(pt.type) <> 'biologico' " +
					"and LOWER(p.name) not like CONCAT('%','vih','%')" +
					ORDER_BY_INIT_DATE_IN_PATIENT_TREATMENT;

	public static final String QUERY_ALL_VIH_TREATMENTS_BY_PATIENT_ID =
			SELECT_PT_FROM_PATIENT_TREATMENT_PT +
					"join PatientDiagnose pd on pt.patientDiagnose.id = pd.id " +
					"join Indication i on pd.indication.id = i.id " +
					"join Pathology p on i.pathologyId = p.id " +
					WHERE_CLAUSULE +
					" pd.patient.id = :patId " +
					"and LOWER(pt.type) = LOWE(:type) " +
					"and LOWER(p.name) like CONCAT('%','vih','%')" +
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

	public static final String QUERY_FIND_PATIENT_BY_CLINICAL_DATA =
			" select pdc from PatientClinicalData pdc join Patient pat on pdc.patient.id = pat.id ";
	public static final String FILTER_PCD_NAME = " upper(pdc.name) = upper(:name) ";
	public static final String FILTER_PCD_PATIENT_ID = " pat.id = :patientId ";
	public static final String FILTER_PCD_VALUE_LIKE = " upper(pdc.value) like upper(:value) ";
	public static final String FILTER_PCD_VALUE_MINUS_THAN = " pdc.value < :value ";
	public static final String FILTER_PCD_VALUE_MORE_THAN = " pdc.value > :value ";
	public static final String FILTER_PCD_VALUE_MORE_BETWEEN = " pdc.value between  :minValue and :maxValue ";
	
	public static final String QUERY_FIND_PHARMACY_BY_DISPENSATION_AND_MEDICINE =
			"select NEW es.plexus.hopes.hopesback.controller.model.PharmacyDTO( dd.nhc , dd.date , m.nationalCode , m.presentation , dd.quantity , dd.amount, m.unitDose, dd.botCode) " +
					"from Medicine m " +
							"join DispensationDetail dd on m.nationalCode = CAST(dd.nationalCode as text) ";

	public static final String QUERY_FIND_PHARMACY_BY_SEARCH =
			"select NEW es.plexus.hopes.hopesback.controller.model.PharmacyDTO( dd.nhc , dd.date , m.nationalCode , m.presentation , dd.quantity , dd.amount, m.unitDose, dd.botCode) " +
					"from Medicine m " +
							"join DispensationDetail dd on m.nationalCode = CAST(dd.nationalCode as text) " +
							"where upper(dd.nhc) like CONCAT('%',upper(:search),'%') " +
							"or to_char(dd.date,'DD/MM/YY') like CONCAT('%',:search,'%') " +
							"or upper(m.nationalCode) like CONCAT('%',upper(:search),'%') " +
							"or upper(m.presentation) like CONCAT('%',upper(:search),'%') " +
							"or upper(dd.quantity) like CONCAT('%',upper(:search),'%') " +
							"or CAST(dd.amount as text) like CONCAT('%',:search,'%') " +
							"or CAST(m.unitDose as text) like CONCAT('%',:search,'%') " +
							"or upper(dd.botCode) like CONCAT('%',upper(:search),'%') ";

}
