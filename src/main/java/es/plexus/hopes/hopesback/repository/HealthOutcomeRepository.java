package es.plexus.hopes.hopesback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;

@Repository
public interface HealthOutcomeRepository extends JpaRepository<HealthOutcome, Long> {

	@Query(QueryConstants.QUERY_FIND_RESULTS_BY_TYPES)
	List<HealthOutcome> findResultsByTypes(@Param("type")String type);
	
	@Query(QueryConstants.QUERY_GET_DETAIL_RESULTS_BY_TYPE)
	Page<GraphPatientDetailDTO> getDetailsResultsByType(@Param("indexType")String indexType, Pageable pageable);
	
	@Query(QueryConstants.QUERY_GET_DETAIL_RESULTS_BY_TYPE)
	List<GraphPatientDetailDTO> getDetailsResultsByType(@Param("indexType")String indexType);
	
	@Query(QueryConstants.QUERY_ALL_PATIENTS_HEALHT_OUTCOME)
	List<Long> getAllPatientsId();
}

