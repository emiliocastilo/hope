package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static es.plexus.hopes.hopesback.repository.utils.QueryConstants.QUERY_PATIENTS_BY_END_CAUSE;
import static es.plexus.hopes.hopesback.repository.utils.QueryConstants.QUERY_PATIENTS_BY_END_CAUSE_IN_LAST_YEARS;
import static es.plexus.hopes.hopesback.repository.utils.QueryConstants.QUERY_PATIENTS_BY_TREATMENT_TYPE_AND_INDICATION;

@Repository
public class PatientRepositoryImpl implements PatientRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Patient> findByPathologies(Collection pathologies, Pageable pageable) {
		return findByPathologies(pathologies,pageable);
	}

	@Override
	public Page<Patient> findPatientBySearch(Collection pathologies, String search, Pageable pageable) {
		return findPatientBySearch(pathologies, search, pageable);
	}

	@Override
	public boolean existsByNhc(String nhc) {
		return existsByNhc(nhc);
	}

	@Override
	public boolean existsByHealthCard(String healthCard) {
		return existsByHealthCard(healthCard);
	}

	@Override
	public boolean existsByDni(String dni) {
		return existsByDni(dni);
	}

	@Override
	public boolean existsByEmail(String email) {
		return existsByEmail(email);
	}

	@Override
	public boolean existsByPhone(String phone) {
		return existsByPhone(phone);
	}

	@Override
	public List<Patient> findPatientsByIndication(String indication) {
		return  findPatientsByIndication(indication);
	}

	@Override
	public List<Patient> findPatientDetailsGraphsByCie9(String cie9) {
		return findPatientDetailsGraphsByCie9(cie9);
	}

	@Override
	public List<Patient> findPatientDetailsGraphsByCie10(String cie10) {
		return findPatientDetailsGraphsByCie10(cie10);
	}

	@Override
	public List<Patient> findPatientDetailsGraphsByTypeTreatment(String treatmentType) {
		return findPatientDetailsGraphsByTypeTreatment(treatmentType);
	}

	@Override
	public List<Patient> findPatientGraphDetailsByCombinedTreatment() {
		return findPatientGraphDetailsByCombinedTreatment();
	}

	@Override
	public List<Patient> findPatientGraphDetailsByNoTreatment() {
		return findPatientGraphDetailsByNoTreatment();
	}

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
	public List<Patient> findGraphPatientsDetailsByPatientsIds(Collection<Long> patientsIds) {
		return findGraphPatientsDetailsByPatientsIds(patientsIds);
	}

	@Override
	public List<Patient> findGraphPatientsDetailsByCombinedTreatments(Collection<String> treatments, Long numberTreatments) {
		return findGraphPatientsDetailsByCombinedTreatments(treatments, numberTreatments);
	}

	@Override
	public List<Patient> getDetailsResultsByType(String indexType, String result) {
		return getDetailsResultsByType(indexType, result);
	}

	@Override
	public List<Patient> getDetailPatientsPerDoses(String regimen) {
		return getDetailPatientsPerDoses(regimen);
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

	@Override
	public List<Patient> findAll() {
		return findAll();
	}

	@Override
	public List<Patient> findAll(Sort sort) {
		return findAll(sort);
	}

	@Override
	public Page<Patient> findAll(Pageable pageable) {
		return findAll(pageable);
	}

	@Override
	public List<Patient> findAllById(Iterable<Long> longs) {
		return findAllById(longs);
	}

	@Override
	public long count() {
		return count();
	}

	@Override
	public void deleteById(Long aLong) {
		deleteById(aLong);
	}

	@Override
	public void delete(Patient entity) {
		delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Patient> entities) {
		deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		deleteAll();
	}

	@Override
	public <S extends Patient> S save(S entity) {
		return save(entity);
	}

	@Override
	public <S extends Patient> List<S> saveAll(Iterable<S> entities) {
		return saveAll(entities);
	}

	@Override
	public Optional<Patient> findById(Long aLong) {
		return Optional.empty();
	}

	@Override
	public boolean existsById(Long aLong) {
		return false;
	}

	@Override
	public void flush() {

	}

	@Override
	public <S extends Patient> S saveAndFlush(S entity) {
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Patient> entities) {
		deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch() {
		deleteAllInBatch();
	}

	@Override
	public Patient getOne(Long aLong) {
		return getOne(aLong);
	}

	@Override
	public <S extends Patient> Optional<S> findOne(Example<S> example) {
		return findOne(example);
	}

	@Override
	public <S extends Patient> List<S> findAll(Example<S> example) {
		return findAll(example);
	}

	@Override
	public <S extends Patient> List<S> findAll(Example<S> example, Sort sort) {
		return findAll(example, sort);
	}

	@Override
	public <S extends Patient> Page<S> findAll(Example<S> example, Pageable pageable) {
		return findAll(example, pageable);
	}

	@Override
	public <S extends Patient> long count(Example<S> example) {
		return count(example);
	}

	@Override
	public <S extends Patient> boolean exists(Example<S> example) {
		return exists(example);
	}
}

