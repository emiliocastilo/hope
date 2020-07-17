package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<PatientDiagnose, Long> {

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSE_BY_INDICATION)
	List<PatientDiagnose> findPatientsDiagnosisGroupByIndication();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSE_GROUP_BY_CIE9)
	List<PatientDiagnose> findPatientsDiagnosisGroupByCie9();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSE_BY_CIE10)
	List<PatientDiagnose> findPatientsDiagnosisGroupByCie10();

    PatientDiagnose findByPatient(Patient patient);
}

