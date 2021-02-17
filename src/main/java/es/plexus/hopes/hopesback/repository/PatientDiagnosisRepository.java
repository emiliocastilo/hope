package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<PatientDiagnose, Long> {

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSE_BY_INDICATION)
	List<PatientDiagnose> findPatientsDiagnosisGroupByIndication();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSE_GROUP_BY_CIE9)
	List<PatientDiagnose> findPatientsDiagnosisGroupByCie9();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSE_BY_CIE10)
	List<PatientDiagnose> findPatientsDiagnosisGroupByCie10();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSE_BY_PATIENT_ID)
	List<PatientDiagnose> findPatientsDiagnosibyPatientId(@Param("patientId") Long patientId);

    PatientDiagnose findByPatient(Patient patient);

    Optional<PatientDiagnose> findByPatientIdAndIndicationId(Long patient, Long indication);

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSE_BY_PATIENT_ID_AND_PATHOLOGY_ID)
    Optional<PatientDiagnose> findByPatientIdAndPathologyId(@Param("patientId") Long patientId,@Param("pathologyId") Long pathologyId);

    PatientDiagnose findByPatientId(long patientId);
}

