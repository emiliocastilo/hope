package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthOutcomeRepository extends JpaRepository<HealthOutcome, Long> {

	@Query(QueryConstants.QUERY_FIND_RESULTS_BY_TYPES)
	List<HealthOutcome> findResultsByTypes(@Param("type")String type);
	
	@Query(QueryConstants.QUERY_ALL_PATIENTS_HEALHT_OUTCOME)
	List<Long> getAllPatientsId();

	@Query(QueryConstants.QUERY_VALUES_HEALHT_OUTCOME_BY_INDEX_TYPE_PATIENT_ID)
	List<HealthOutcome> findEvolutionClinicalIndicesByIndexTypeAndPatient(@Param("indexType")String indexType,
																		  @Param("patId")Long patId);
}

