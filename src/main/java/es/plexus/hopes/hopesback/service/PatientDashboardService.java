package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.controller.model.GraphHealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDashboardDetailDTO;
import es.plexus.hopes.hopesback.service.mapper.DispensationDetailDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientDashboardService {
	private static final String INDICES_TYPES = "DLQI, PASI";

	private final HealthOutcomeService healthOutcomeService;
	private final PatientTreatmentService patientTreatmentService;
	private final DispensationDetailService dispensationDetailServices;

	public PatientDashboardDetailDTO findDashboardPatientByPatientId(Long patId) {
		PatientDashboardDetailDTO patientDashboardDetailDto = new PatientDashboardDetailDTO();

		// Fill the indices evolution
		Map<String, List<GraphHealthOutcomeDTO>> indicesEvolutionMap =
				healthOutcomeService.findEvolutionClinicalIndicesByIndexTypeAndPatient(INDICES_TYPES, patId);
		patientDashboardDetailDto.setIndicesEvolution(indicesEvolutionMap);

		// Fill the treatments
		patientDashboardDetailDto.setTreatments(patientTreatmentService.findTreatmentsByPatientId(patId));

		// Add the adherence
		List<DispensationDetailDTO> dispensationsDetails = dispensationDetailServices
																.findDispensationDetailByPatientId(patId);
		patientDashboardDetailDto
				.setAdherence(dispensationsDetails
								.stream()
								.map(Mappers.getMapper(DispensationDetailDTOMapper.class)::dispensationDetailDTOToAdherenceDTO)
								.collect(Collectors.toList()));
		return patientDashboardDetailDto;
	}

	public Map<String, List<GraphHealthOutcomeDTO>> findEvolutionClinicalIndicesByIndexTypeAndPatient(
			String indicesTypes, Long patId) {

		return healthOutcomeService.findEvolutionClinicalIndicesByIndexTypeAndPatient(indicesTypes, patId);

	}

}
