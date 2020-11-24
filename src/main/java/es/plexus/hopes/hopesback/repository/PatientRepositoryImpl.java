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
		TypedQuery<Patient> query;
		String queryStr;

		if (StringUtils.isBlank(medicine)){
			queryStr = QUERY_PATIENTS_BY_TREATMENT_TYPE_AND_INDICATION;
		} else {
			queryStr = QUERY_PATIENTS_BY_TREATMENT_TYPE_AND_INDICATION_AND_MEDICINE;
		}

		query = entityManager.createQuery(queryStr, Patient.class)
				.setParameter("type", type)
				.setParameter("indication", indication);

		if (StringUtils.isNotBlank(medicine)){
			query.setParameter("medicine", medicine);
		}

		result = query.getResultList();
		return result;
	}

}

