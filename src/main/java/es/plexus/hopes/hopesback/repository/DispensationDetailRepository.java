package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.DispensationDetail;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
	
	@Query(QueryConstants.QUERY_NUMBER_PATIENTS_MONTH)
	List<String> findPatiensMonth(@Param("dateStart")LocalDateTime dateStart, @Param("dateEnd")LocalDateTime dateEnd);
	
	/*@Query(QueryConstants.QUERY_FIND_RESULTS_ECO_TREATMENT_BY_MONTH)
	Double findResultsAllPatiensByMonth(@Param("code")String code, @Param("dateStart")LocalDateTime dateStart, @Param("dateEnd")LocalDateTime dateEnd);*/
	
	@Query(QueryConstants.QUERY_FIND_RESULTS_ALL_PATIENTS_BY_MONTH)
	Double findResultsAllPatiensByMonth(@Param("dateStart")LocalDateTime dateStart, @Param("dateEnd")LocalDateTime dateEnd, @Param("code")String code);
	
	@Query(QueryConstants.QUERY_FIND_RESULTS_PASI_PATIENTS_BY_MONTH)
	Double findResultsAllPasiPatiensByMonth(@Param("date")LocalDateTime date,
			@Param("dateStart")LocalDateTime dateStart, @Param("dateEnd")LocalDateTime dateEnd,
			@Param("patient")Long patient,  @Param("code")String code);

	@Query(QueryConstants.QUERY_FIND_DISPENSATION_DETAILS_BY_PATIENT_ID)
	List<DispensationDetail> findDispensationDetailByPatientId(@Param("patId")Long patId);

	List<DispensationDetail> findDispensationDetailByNhc(String nhc);
}
