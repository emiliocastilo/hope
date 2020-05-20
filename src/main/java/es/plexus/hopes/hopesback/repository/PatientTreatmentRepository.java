package es.plexus.hopes.hopesback.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;

@Repository
public interface PatientTreatmentRepository extends JpaRepository<PatientTreatment, Long> {
	
	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_WITHOUT_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByWithoutTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_COMBINED_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByCombinedTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE)
	List<PatientTreatment> findPatientTreatmentByEndCauseBiologicTreatment(@Param("endCause")String endCause);

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_BIOLOGICAL_TREATMENT_END_CAUSE_IN_LAST_5_YEARS)
	List<PatientTreatment> findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(@Param("endCause")String endCause, @Param("initDate")LocalDateTime initDate);

	@Query(QueryConstants.QUERY_PATIENTS_DIAGNOSES_BY_NUMBER_CHANGES_OF_BIOLOGICAL_TREATMENT)
	List<PatientTreatment> findPatientTreatmentByNumberChangesOfBiologicTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION)
	List<PatientTreatment> findPatientsUnderTreatment(@Param("type")String type, @Param("indication")String indication);
	
	@Query(QueryConstants.QUERY_FIND_INFO_PATIENTS_DOSES)
	List<PatientTreatment> findInfoPatientsDoses();
	
	@Query(QueryConstants.QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION)
	Page<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(@Param("type")String type, @Param("indication")String indication, Pageable pageable);
	
	@Query(QueryConstants.QUERY_PATIENTS_TREAMENT_BY_TYPE_AND_INDICATION)
	List<GraphPatientDetailDTO> getDetailPatientsUnderTreatment(@Param("type")String type, @Param("indication")String indication);
	
	@Query(QueryConstants.QUERY_PATIENTS_TREAMENT_PER_DOSES)
	Page<GraphPatientDetailDTO> getDetailPatientsPerDoses(Pageable pageable);
	
	@Query(QueryConstants.QUERY_PATIENTS_TREAMENT_PER_DOSES)
	List<GraphPatientDetailDTO> getDetailPatientsPerDoses();
}

