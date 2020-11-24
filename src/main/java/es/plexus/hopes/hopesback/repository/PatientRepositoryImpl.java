package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

import static es.plexus.hopes.hopesback.repository.utils.QueryConstants.*;

@Repository
public class PatientRepositoryImpl implements PatientRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Patient> findGraphPatientsDetailsByEndCauseBiologicTreatment(String endCause, String reason) {
		List<Patient> result = entityManager
									.createQuery(QUERY_PATIENTS_BY_END_CAUSE, Patient.class)
									.setParameter("endCause", endCause)
									.setParameter("reason", reason)
									.setMaxResults(1)
				.getResultList();

		return result;
	}

	@Override
	public List<Patient> findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears(String endCause, String reason, LocalDateTime initDate) {
		List<Patient> result = entityManager
				.createQuery(QUERY_PATIENTS_BY_END_CAUSE_IN_LAST_YEARS, Patient.class)
				.setParameter("endCause", endCause)
				.setParameter("reason", reason)
				.setParameter("initDate", initDate)
				.setMaxResults(1)
				.getResultList();
		return result;
	}


	@Override
	public List<Patient> getDetailPatientsUnderTreatment(String type, String indication, String medicine) {
		List<Patient> result;
		TypedQuery<Patient> query = getQueryDetailPatientsUnderTreatment(type, indication, medicine);

		result = query.getResultList();
		return result;
	}

	private TypedQuery<Patient> getQueryDetailPatientsUnderTreatment(String type, String indication, String medicine){
		String queryStr = getQueryStringDetailPatientsUnderTreatment(StringUtils.isNotBlank(indication), StringUtils.isNotBlank(medicine));
		TypedQuery<Patient> query = entityManager.createQuery(queryStr, Patient.class).setParameter("type", type);

		if (StringUtils.isNotBlank(indication)) {
			query.setParameter("indication", indication);
		}

		if (StringUtils.isNotBlank(medicine)) {
			query.setParameter("medicine", medicine);
		}

		return query;
	}

	private String getQueryStringDetailPatientsUnderTreatment( boolean indication, boolean medicine){
		String queryStr = QUERY_PATIENTS_BY_TREATMENT_TYPE_AND_INDICATION;
		String joinString = "";
		String whereString = WHERE_CLAUSULE + FILTER_PTR_ACTIVE_TRUE +FILTER_PTR_TYPE ;

		if ( indication ){
			joinString += JOIN_FETCH_INDICATION_IND_ON_IND_ID_PDG_INDICATION_ID;
			whereString += FILTER_INDICATION_DESCRIPTION;
		}
		if ( medicine ){
			joinString += JOIN_MEDICINES_MED_ON_MED_MED_ID_PTR_MED_ID;
			whereString += FILTER_MEDICINE_ACT_INGREDIENTS;
		}

		queryStr += joinString + whereString;
		return queryStr;
	}


}

