package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.DispensationDetail;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispensationDetailRepository extends JpaRepository<DispensationDetail, Long> {
	@Query("select dd from DispensationDetail dd join dd.dispensation dis WHERE dis.id = :id")
	List<DispensationDetail> findByDispensation(@Param("id")Long id);

	@Query("select dd from DispensationDetail dd join dd.dispensation dis WHERE dis.id = :id")
	Page<DispensationDetail> findByDispensation(@Param("id")Long id, Pageable pageable);

	@Query("select dd from DispensationDetail dd "
			+ " WHERE CAST(dd.date as text) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(dd.nhc) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(dd.code) like CONCAT('%',LOWER(:search),'%') "
			+ " OR CAST(dd.nationalCode as text) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(dd.description) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(dd.quantity) like CONCAT('%',LOWER(:search),'%') "
			+ " OR CAST(dd.amount as text) like CONCAT('%',LOWER(:search),'%') "
			+ " OR CAST(dd.daysDispensation as text) like CONCAT('%',LOWER(:search),'%') ")
	Page<DispensationDetail> findDispensationDetailBySearch(@Param("search")String search, Pageable pageable);
	
	@Query(QueryConstants.QUERY_FIND_RESULTS_BY_TYPES)
	List<HealthOutcome> findResultsAllPatiensByMonth(@Param("month")String month);
}
