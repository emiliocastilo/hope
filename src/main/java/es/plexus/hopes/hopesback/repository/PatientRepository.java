package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>,PatientRepositoryCustom {

	@Query("select pac from Patient pac join pac.pathologies pat WHERE pat.id in (:pathologies)")
	Page<Patient> findByPathologies(@Param("pathologies") Collection pathologies, Pageable pageable);
	
	@Query("select pac from Patient pac join pac.pathologies pat WHERE pat.id in (:pathologies) "
			+ " AND (LOWER(pac.name) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.firstSurname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.lastSurname) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.nhc) like CONCAT('%',LOWER(:search),'%') "
			+ " OR LOWER(pac.healthCard) like CONCAT('%',LOWER(:search),'%')) ")
	Page<Patient> findPatientBySearch(@Param("pathologies") Collection pathologies, @Param("search")String search,
									  Pageable pageable);

	boolean existsByNhc(String nhc);

	boolean existsByHealthCard(String healthCard);

	boolean existsByDni(String dni);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	@Query(QueryConstants.QUERY_PATIENTS_BY_INDICATIONS)
	List<Patient> findPatientsByIndication(@Param("indication") String indication);

	@Query(QueryConstants.QUERY_PATIENTS_BY_CIE_9)
	List<Patient> findPatientDetailsGraphsByCie9(@Param("cie9") String cie9);

	@Query(QueryConstants.QUERY_PATIENTS_BY_CIE_10)
	List<Patient> findPatientDetailsGraphsByCie10(@Param("cie10") String cie10);

	@Query(QueryConstants.QUERY_PATIENTS_BY_TREATMENT)
	List<Patient> findPatientDetailsGraphsByTypeTreatment(@Param("treatmentType") String treatmentType);

	@Query(QueryConstants.QUERY_PATIENTS_BY_COMBINED_TREATMENT)
	List<Patient> findPatientGraphDetailsByCombinedTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_BY_NO_TREATMENT)
	List<Patient> findPatientGraphDetailsByNoTreatment();

	@Query(QueryConstants.QUERY_PATIENTS_BY_PATIENTS_ID)
	List<Patient> findGraphPatientsDetailsByPatientsIds(@Param("patientsIds")Collection<Long> patientsIds);

	@Query(QueryConstants.QUERY_PATIENTS_BY_COMBINED_TREATMENTS)
	List<Patient> findGraphPatientsDetailsByCombinedTreatments(@Param("treatments")Collection<String> treatments,
															   @Param("numberTreatments")Long numberTreatments);

	@Query(QueryConstants.QUERY_GET_DETAIL_RESULTS_BY_TYPE)
	List<Patient> getDetailsResultsByType(@Param("indexType")String indexType, @Param("result") String result);

	@Query(QueryConstants.QUERY_PATIENTS_TREAMENT_PER_DOSES)
	List<Patient> getDetailPatientsPerDoses(@Param("regimen")String regimen);

}

