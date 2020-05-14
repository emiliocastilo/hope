package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<PatientDiagnose, Long> {

	@Query("select pd.indication, count(*) from PatientDiagnose pd group by pd.indication")
	List<PatientDiagnose> patientsByIndication();

	@Query("select pd.cie9Code, pd.cie9Desc, count(*) as numberPatients from PatientDiagnose pd group by pd.cie9Code, pd.cie9Desc")
	List<PatientDiagnose> patientsByCIE9();

	@Query("select pd.cie10Code, pd.cie10Desc, count(*) as numberPatients from PatientDiagnose pd group by pd.cie10Code, pd.cie10Desc")
	List<PatientDiagnose> patientsByCIE10();
	
}

