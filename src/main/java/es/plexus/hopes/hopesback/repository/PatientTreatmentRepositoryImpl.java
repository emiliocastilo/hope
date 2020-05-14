package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.PatientTreatment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PatientTreatmentRepositoryImpl {
	@PersistenceContext
	private EntityManager entityManager;

	List<PatientTreatment> findPatientTreatmentByTreatment(){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientTreatment> query= cb.createQuery(PatientTreatment.class);
		Root<PatientTreatment> root = query.from(PatientTreatment.class);

		List<Predicate> predicates = new ArrayList<>();
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getResultList();
	}

}

