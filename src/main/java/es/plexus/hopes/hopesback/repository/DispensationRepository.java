package es.plexus.hopes.hopesback.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.repository.model.Dispensation;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;

@Repository
public interface DispensationRepository extends JpaRepository<Dispensation, Long> {

	@Query(QueryConstants.QUERY_FIND_RESULTS_ALL_PATIENTS_BY_MONTH)
	Double findResultsAllPatiensByMonth(@Param("dateStart")LocalDateTime dateStart, @Param("dateEnd")LocalDateTime dateEnd, @Param("code")String code);
	
	@Query(QueryConstants.QUERY_PATIENTS_WITH_PASI)
	List<HealthOutcome> findPatiensWithPasi(@Param("date")LocalDateTime date);
	
	@Query(QueryConstants.QUERY_FIND_RESULTS_PASI_PATIENTS_BY_MONTH)
	Long findResultsAllPasiPatiensByMonth(@Param("dateStart")LocalDateTime dateStart, 
			@Param("dateEnd")LocalDateTime dateEnd, @Param("code")String code, @Param("patient")Long patient);
	
}

