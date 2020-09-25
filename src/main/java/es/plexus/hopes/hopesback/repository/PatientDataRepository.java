package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientData;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.utils.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PatientDataRepository extends JpaRepository<PatientData, Long> {

	@Query(QueryConstants.QUERY_PATIENTS_DATA_BY_PATIENTS_ID)
	List<PatientData> findPatientsDatasByPatientsIds(@Param("patientsIds") Collection<Long> patientsIds);
}

