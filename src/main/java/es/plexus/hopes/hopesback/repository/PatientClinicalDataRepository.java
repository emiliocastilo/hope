package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.PatientClinicalData;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PatientClinicalDataRepository extends JpaRepository<PatientClinicalData, Long> {
    @Query(QueryConstants.QUERY_FIND_PATIENT_BY_CLINICAL_DATA + QueryConstants.WHERE_CLAUSULE + QueryConstants.FILTER_PCD_NAME)
    List<PatientClinicalData> findByPCDName(@Param("name") String pcdName);

    @Query(QueryConstants.QUERY_FIND_PATIENT_BY_CLINICAL_DATA +  QueryConstants.WHERE_CLAUSULE + QueryConstants.FILTER_PCD_NAME + " AND " + QueryConstants.FILTER_PCD_VALUE)
    List<PatientClinicalData> findByPCDNameAndValues(@Param("name") String pcdName,@Param("values") Collection<String> values);
}
