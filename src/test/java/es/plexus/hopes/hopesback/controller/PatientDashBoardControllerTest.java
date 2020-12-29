package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphHealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.MedicineDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDashboardDetailDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentDTO;
import es.plexus.hopes.hopesback.service.PatientDashboardService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientDashBoardControllerTest {

	@Mock
	private PatientDashboardService patientDashboardService;

	@InjectMocks
	private PatientDashboardController patientDashBoardController;

	@Test
	public void callFindDashboardPatientByPatientIdShouldBeStatusOk() {

		// given
		given(patientDashboardService.findDashboardPatientByPatientId(anyLong(), anyString()))
				.willReturn(mockPatientDashboardDetailDTO());

		PatientDashboardDetailDTO response = patientDashBoardController
				.findDashboardPatientByPatientId(3L, "token");

		// then
		Assert.assertNotNull(response);
	}

	@Test(expected = ServiceException.class)
	public void callFindDashboardPatientByPatientIdThrowException() throws ServiceException {
		// given
		given(patientDashboardService.findDashboardPatientByPatientId(anyLong(), anyString()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		patientDashBoardController.findDashboardPatientByPatientId(3L, "token");

	}

	@Test
	public void callFindEvolutionClinicalIndicesResultsByIndexTypeAndPatientShouldBeStatusOk() {

		// given
		given(patientDashboardService.findEvolutionClinicalIndicesByIndexTypeAndPatient(anyString(), anyLong()))
				.willReturn(mockMapEvolutionIndices());

		Map<String,List<GraphHealthOutcomeDTO>> response = patientDashBoardController
				.findEvolutionClinicalIndicesResultsByIndexTypeAndPatient("PASI", 3L);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindEvolutionClinicalIndicesResultsByIndexTypeAndPatientThrowException() throws ServiceException {
		// given
		given(patientDashboardService.findEvolutionClinicalIndicesByIndexTypeAndPatient(anyString(), anyLong()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		patientDashBoardController.findEvolutionClinicalIndicesResultsByIndexTypeAndPatient("PASI", 3L);

	}

	//Mocks
	private PatientDashboardDetailDTO mockPatientDashboardDetailDTO(){
		PatientDashboardDetailDTO patientDashboardDetailDTO = new PatientDashboardDetailDTO();
		TreatmentDTO treatment = new TreatmentDTO();
		treatment.setActive(true);
		treatment.setDose("dose");
		treatment.setInitDate(LocalDateTime.now());
		treatment.setMedicine(new MedicineDTO());
		treatment.setType("type");
		treatment.setFinalDate(LocalDateTime.now());
		Map<String, List<TreatmentDTO>> map = Collections.singletonMap("BIOLOGICAL", Collections.singletonList(treatment));
		patientDashboardDetailDTO.setTreatments(map);

		Map<String, List<GraphHealthOutcomeDTO>> indicesEvolution = new HashMap<>();
		indicesEvolution.put("DLQI", Collections.singletonList(new GraphHealthOutcomeDTO()));
		patientDashboardDetailDTO.setIndicesEvolution(indicesEvolution);

		return patientDashboardDetailDTO;
	}
	private GraphHealthOutcomeDTO mockGraphHealthOutcomeDTO() {
		GraphHealthOutcomeDTO graphHealthOutcomeDTO= new GraphHealthOutcomeDTO();
		graphHealthOutcomeDTO.setIndexType("indexType");
		graphHealthOutcomeDTO.setValue(BigDecimal.ONE);
		graphHealthOutcomeDTO.setDate(LocalDateTime.now());
		return	graphHealthOutcomeDTO;
	}

	private Map<String,List<GraphHealthOutcomeDTO>> mockMapEvolutionIndices() {
		Map<String, List<GraphHealthOutcomeDTO>> map = new HashMap<>();
		map.put("PASI", Collections.singletonList(mockGraphHealthOutcomeDTO()));
		return map;
	}

}

