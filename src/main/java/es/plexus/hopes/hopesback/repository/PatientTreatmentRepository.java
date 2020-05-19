package es.plexus.hopes.hopesback.repository;

import static es.plexus.hopes.hopesback.repository.ConstantsPatientTreatmentQuerys.QUERY_DETAIL_TRATMENTS_INFO;
import static es.plexus.hopes.hopesback.repository.ConstantsPatientTreatmentQuerys.QUERY_INFO_PATIENTS_DOSES;
import static es.plexus.hopes.hopesback.repository.ConstantsPatientTreatmentQuerys.QUERY_PATIENTS_UNDER_TRATMENT;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;


@Repository
public interface PatientTreatmentRepository extends JpaRepository<PatientTreatment, Long> {

	@Query(QUERY_PATIENTS_UNDER_TRATMENT)
	List<TreatmentInfoDTO> patientsUnderTreatment(@Param("type")String type, @Param("indication")String indication);
	
	@Query(QUERY_INFO_PATIENTS_DOSES)
	List<PatientDosesInfoDTO> infoPatientsDoses();
	
	@Query(QUERY_DETAIL_TRATMENTS_INFO)
	//Page<Object[]> detailsGrapths(@Param("type")String type, @Param("indication")String indication, Pageable pageable);
	//Prueba
	Page<DetailGraphDTO> detailsGrapths(@Param("type")String type, @Param("indication")String indication, Pageable pageable);
}

