package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface PatientTreatmentRepository extends JpaRepository<PatientTreatment, Long> {

	@Query(QueryConstants.QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION)
	List<TreatmentInfoDTO> patientsUnderTreatment(@Param("type")String type, @Param("indication")String indication);

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_COMBINED_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByCombinedTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE)
	List<PatientTreatment> findPatientTreatmentByEndCauseBiologicTreatment(@Param("endCause")String endCause);

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE_IN_LAST_5_YEARS)
	List<PatientTreatment> findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(@Param("endCause")String endCause);

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_NUMBER_CHANGES_OF_BIOLOGICAL_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByNumberChangesOfBiologicTreatment();
}

