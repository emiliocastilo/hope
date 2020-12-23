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

    @Query(QueryConstants.QUERY_FIND_PATIENT_BY_CLINICAL_DATA +  QueryConstants.WHERE_CLAUSULE + QueryConstants.FILTER_PCD_NAME + " AND " + QueryConstants.FILTER_PCD_VALUE_LIKE)
    List<PatientClinicalData> findByPCDNameAndIndicationLike(@Param("name") String pcdName, @Param("value") String value);

    @Query(QueryConstants.QUERY_FIND_PATIENT_BY_CLINICAL_DATA +  QueryConstants.WHERE_CLAUSULE + QueryConstants.FILTER_PCD_NAME + " AND " + QueryConstants.FILTER_PCD_VALUE_MINUS_THAN)
    List<PatientClinicalData> findByPCDNameAndIndicationMinusThan(@Param("name") String pcdName, @Param("value") String value);

    @Query(QueryConstants.QUERY_FIND_PATIENT_BY_CLINICAL_DATA +  QueryConstants.WHERE_CLAUSULE + QueryConstants.FILTER_PCD_NAME + " AND " + QueryConstants.FILTER_PCD_VALUE_MORE_THAN)
    List<PatientClinicalData> findByPCDNameAndIndicationMoreThan(@Param("name") String pcdName, @Param("value") String value);

    @Query(QueryConstants.QUERY_FIND_PATIENT_BY_CLINICAL_DATA +  QueryConstants.WHERE_CLAUSULE + QueryConstants.FILTER_PCD_NAME + " AND " + QueryConstants.FILTER_PCD_VALUE_MORE_BETWEEN)
    List<PatientClinicalData> findByPCDNameAndIndicationBetween(@Param("name") String pcdName, @Param("minValue") String minValue, @Param("maxValue") String maxValue);

    @Query(QueryConstants.QUERY_FIND_PATIENT_BY_CLINICAL_DATA +  QueryConstants.WHERE_CLAUSULE + QueryConstants.FILTER_PCD_NAME + " AND " + QueryConstants.FILTER_PCD_PATIENT_ID)
    PatientClinicalData findByPCDNameAndPatientId(@Param("name") String pcdName, @Param("patientId") Long patientId);

    List<PatientClinicalData> findByName(String cvp);

    List<PatientClinicalData> findByNameAndValueLike(String name, String value);

}
