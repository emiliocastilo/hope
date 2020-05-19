package es.plexus.hopes.hopesback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class GraphsService {
	
	private static final String CALLING_DB = "Calling DB...";

	private final PatientTreatmentRepository patientTreatmentRepository;

	public List<PatientDosesInfoDTO> infoPatientsDoses() {
		log.debug(CALLING_DB);
		return patientTreatmentRepository.infoPatientsDoses();
	}

	/*public Page<DetailGraphDTO> detailsGrapths(final String type, final String  indication, final Pageable pageable) {
		log.debug(CALLING_DB);
		//Page<Object[]>detailGraph = patientTreatmentRepository.detailsGrapths(type, indication, pageable);
		//return detailGraph.map(DetailGraphDTOMapper.INSTANCE::objectToDetailGraphDTOConventer);
		return patientTreatmentRepository.detailsGrapths(type, indication, pageable);
	}*/
}
