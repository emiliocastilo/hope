package es.plexus.hopes.hopesback.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import es.plexus.hopes.hopesback.controller.model.HealthOutcomeDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.service.GraphsService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GraphsControllerTest {

	@Mock
	private GraphsService graphsService;

	@InjectMocks
	private GraphsController graphsController;

	@Test
	public void callPatientsUnderChemicalTreatmentShouldBeStatusOk() {

		// given
		given(graphsService.patientsUnderChemicalTreatment(anyString(), anyString()))
				.willReturn(mockTreatmentList());

		// when
		List<TreatmentInfoDTO> response = graphsController.patientsUnderChemicalTreatment(anyString(), anyString());

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callPatientsUnderChemicalTreatmentThrowException() throws Exception {
		// given
		given(graphsService.patientsUnderChemicalTreatment(anyString(), anyString()))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		List<TreatmentInfoDTO> response = graphsController.patientsUnderChemicalTreatment(anyString(), anyString());

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}

	@Test
	public void callHealthOutcomesByTypeShouldBeStatusOk() {

		// given
		given(graphsService.healthOutcomesByType(anyString()))
				.willReturn(mockHealthOutcomeList());

		// when
		List<HealthOutcomeDTO> response = graphsController.healthOutcomesByType(anyString());

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}
		
	@Test(expected = ServiceException.class)
	public void callHealthOutcomesByTypeThrowException() throws Exception {
		// given
		given(graphsService.healthOutcomesByType(anyString()))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		List<HealthOutcomeDTO> response = graphsController.healthOutcomesByType(anyString());

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}
	
	//Mocks
	private List<TreatmentInfoDTO> mockTreatmentList() {
		List<TreatmentInfoDTO> treatmentInfoDTOList = new ArrayList<TreatmentInfoDTO>();

		TreatmentInfoDTO treatmentInfoDTO = mockTreatmentInfoDTO();
		treatmentInfoDTOList.add(treatmentInfoDTO);

		return  treatmentInfoDTOList;
	}


	private TreatmentInfoDTO mockTreatmentInfoDTO() {
		TreatmentInfoDTO treatmentInfoDTO = new TreatmentInfoDTO("ACT code", "Act Ingredient", 1L);
		return treatmentInfoDTO;
	}
	
	private List<HealthOutcomeDTO> mockHealthOutcomeList() {
		List<HealthOutcomeDTO> healthOutcomeDTOList = new ArrayList<HealthOutcomeDTO>();

		HealthOutcomeDTO healthOutcomeDTO = mockHealthOutcomeDTO();
		healthOutcomeDTOList.add(healthOutcomeDTO);

		return  healthOutcomeDTOList;
	}


	private HealthOutcomeDTO mockHealthOutcomeDTO() {
		HealthOutcomeDTO healthOutcomeDTO = new HealthOutcomeDTO("Leve", 1L);
		return healthOutcomeDTO;
	}

}

