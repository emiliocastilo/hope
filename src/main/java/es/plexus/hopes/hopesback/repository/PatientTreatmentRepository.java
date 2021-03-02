package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientTreatmentRepository extends JpaRepository<PatientTreatment, Long> {
	
	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_WITHOUT_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByWithoutTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_COMBINED_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByCombinedTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE)
	List<PatientTreatment> findPatientTreatmentByEndCauseBiologicTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE_IN_LAST_5_YEARS)
	List<PatientTreatment> findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(
			@Param("endCause")String endCause, @Param("initDate")LocalDateTime initDate);

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_NUMBER_CHANGES_OF_BIOLOGICAL_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByNumberChangesOfBiologicTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_NO_CHANGES_BIOLOGICAL_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByNoChangesBiologicTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_TREATMENTS_BY_TREATMENT_TYPE)
	List<PatientTreatment> findPatientsUnderTreatment(@Param("type")String type);

	@Query(QueryConstants.QUERY_PATIENTS_TREATMENTS_BY_TREATMENT_TYPE_AND_INDICATION)
	List<PatientTreatment> findPatientsUnderTreatment(@Param("type")String type, @Param("indication")String indication);

	@Query(QueryConstants.QUERY_FIND_INFO_PATIENTS_DOSES)
	List<PatientTreatment> findInfoPatientsDoses();

	@Query(QueryConstants.QUERY_FIND_INFO_PATIENTS_DOSES_AND_MEDICINES)
	List<PatientTreatment> findInfoPatientsDosesWithMedicines();

	@Query(QueryConstants.QUERY_ACTUAL_TREATMENTS_BY_PATIENT_ID)
	List<PatientTreatment> findTreatmentsByPatientId(@Param("patId") Long patId);

	@Query(QueryConstants.QUERY_ALL_BIOLOGICAL_TREATMENTS_BY_PATIENT_ID)
	List<PatientTreatment> findBiologicalTreatmentsByPatientId(@Param("patId") Long patId);

	@Query(QueryConstants.QUERY_ALL_FAME_DERMA_TREATMENTS_BY_PATIENT_ID)
	List<PatientTreatment> findFameTreatmentsByPatientId(@Param("patId") Long patId);

	@Query(QueryConstants.QUERY_ALL_VIH_TREATMENTS_BY_PATIENT_ID)
	List<PatientTreatment> findVIHTreatmentsByPatientIdAndType(@Param("patId") Long patId, @Param("type") String type);

	Optional<PatientTreatment> findByPatientDiagnoseAndMedicineAndInitDateAndTypeIgnoreCase(PatientDiagnose patientDiagnose, Medicine medicine, LocalDateTime initDateTreatment, String treatmentType);

	Optional<PatientTreatment> findByPatientDiagnoseAndMasterFormulaIgnoreCaseAndMasterFormulaDoseIgnoreCaseAndTypeIgnoreCase(PatientDiagnose patientDiagnose, String masterFormula, String masterFormulaDose, String treatmentType);

	List<PatientTreatment> findByPatientDiagnose(PatientDiagnose patientDiagnose);
}

