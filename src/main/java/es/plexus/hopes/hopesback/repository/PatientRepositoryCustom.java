package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Patient;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientRepositoryCustom {


	List<Patient> findGraphPatientsDetailsByEndCauseBiologicTreatment(
			String endCause, String reason);

	List<Patient> findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears(
			@Param("endCause")String endCause, @Param("reason")String reason, @Param("initDate") LocalDateTime initDate);

	List<Patient> getDetailPatientsUnderTreatment(@Param("type")String type, @Param("indication")String indication,@Param("medicine") Long medicine);

}

