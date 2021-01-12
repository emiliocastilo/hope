package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.controller.model.GraphHealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.PatientClinicalDataDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDashboardDetailDTO;
import es.plexus.hopes.hopesback.repository.RoleRepository;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.DispensationDetailDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientDashboardService {
	private static final String INDICES_TYPES = "DLQI, PASI";
	private static final String NAME_TYPES = "CD4, CVP, glomerularFiltering";

	private final HealthOutcomeService healthOutcomeService;
	private final PatientTreatmentService patientTreatmentService;
	private final DispensationDetailService dispensationDetailServices;
	private final RoleRepository roleRepository;
	private final PatientsClinicalDataService patientsClinicalDataService;

	public PatientDashboardDetailDTO findDashboardPatientByPatientId(Long patId, String token) {
		String roleCode = TokenProvider.getRoleSelected(token);
		Optional<Role> role = roleRepository.findByCode(roleCode);
		Pathology pathology;
		PatientDashboardDetailDTO patientDashboardDetailDto = new PatientDashboardDetailDTO();

		if (role.isPresent()) {
			pathology = role.get().getPathology();
		} else {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception("No se ha encontrado el rol con el code: " + roleCode);
		}

		if (!pathology.getName().equalsIgnoreCase("VIH")) {

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
		} else {
			// Fill the Clinical Data
			Map<String, List<PatientClinicalDataDTO>> graphClinicalData =
					patientsClinicalDataService.findEvolutionClinicalDataByNameAndPatient(NAME_TYPES, patId);
			patientDashboardDetailDto.setGraphClinicalData(graphClinicalData);

			// Fill the treatments
			patientDashboardDetailDto.setTreatments(patientTreatmentService.findTreatmentsByPatientId(patId));
		}
		return patientDashboardDetailDto;
	}

	public Map<String, List<GraphHealthOutcomeDTO>> findEvolutionClinicalIndicesByIndexTypeAndPatient(
			String indicesTypes, Long patId) {

		return healthOutcomeService.findEvolutionClinicalIndicesByIndexTypeAndPatient(indicesTypes, patId);

	}

}
