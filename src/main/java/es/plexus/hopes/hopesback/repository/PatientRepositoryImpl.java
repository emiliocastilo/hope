package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

import static es.plexus.hopes.hopesback.repository.utils.QueryConstants.QUERY_PATIENTS_BY_END_CAUSE;
import static es.plexus.hopes.hopesback.repository.utils.QueryConstants.QUERY_PATIENTS_BY_END_CAUSE_IN_LAST_YEARS;
import static es.plexus.hopes.hopesback.repository.utils.QueryConstants.QUERY_PATIENTS_BY_TREATMENT_TYPE_AND_INDICATION;

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
	public List<Patient> getDetailPatientsUnderTreatment(String type, String indication) {
		List<Patient> result;
		TypedQuery<Patient> query;
		query = entityManager.createQuery(QUERY_PATIENTS_BY_TREATMENT_TYPE_AND_INDICATION, Patient.class)
						.setParameter("type", type)
						.setParameter("indication", indication);

		result = query.getResultList();
		return result;
	}

}

