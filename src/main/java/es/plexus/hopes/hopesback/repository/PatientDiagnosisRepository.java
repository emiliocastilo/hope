package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<PatientDiagnose, Long> {

	@Query(QueryConstants.QUERY_PATIENTS_GRAPH_DETAILS_BY_INDICATIONS)
	Page<GraphPatientDetailDTO> findPatientDetailsGraphsByIndication(@Param("indication") String indication, Pageable pageable);

	@Query(QueryConstants.QUERY_PATIENTS_GRAPH_DETAILS_BY_INDICATIONS)
	List<GraphPatientDetailDTO> findPatientDetailsGraphsByIndication(@Param("indication") String indication);

	@Query(QueryConstants.QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE9)
	Page<GraphPatientDetailDTO> findPatientDetailsGraphsByCie9(@Param("cie9") String cie9, Pageable pageable);

	@Query(QueryConstants.QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE9)
	List<GraphPatientDetailDTO> findPatientDetailsGraphsByCie9(@Param("cie9") String cie9);

	@Query(QueryConstants.QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE10)
	Page<GraphPatientDetailDTO> findPatientDetailsGraphsByCie10(@Param("cie10") String cie10, Pageable pageable);

	@Query(QueryConstants.QUERY_PATIENTS_GRAPH_DETAILS_BY_CIE10)
	List<GraphPatientDetailDTO> findPatientDetailsGraphsByCie10(@Param("cie10") String cie10);

}

