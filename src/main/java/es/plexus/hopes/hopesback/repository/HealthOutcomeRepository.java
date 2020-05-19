package es.plexus.hopes.hopesback.repository;

import static es.plexus.hopes.hopesback.repository.ConstantsHealthOutcomeQuerys.QUERY_DETAIL_RESULTS_INFO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;

@Repository
public interface HealthOutcomeRepository extends JpaRepository<HealthOutcome, Long> {

	@Query(QueryConstants.QUERY_FIND_RESULTS_BY_TYPES)
	List<HealthOutcome> findResultsByTypes(@Param("type")String type);
	
	@Query(QUERY_DETAIL_RESULTS_INFO)
	Page<Object[]> detailsResults(@Param("type")String type, Pageable pageable);
}

