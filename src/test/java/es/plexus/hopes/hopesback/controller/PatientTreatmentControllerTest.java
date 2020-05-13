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

import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientTreatmentControllerTest {

	@Mock
	private PatientTreatmentService patientTreatmentService;

	@InjectMocks
	private PatientTreatmentController patientTreatmentController;

	@Test
	public void callPatientsUnderChemicalTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.patientsUnderChemicalTreatment(anyString(), anyString()))
				.willReturn(mockTreatmentList());

		// when
		List<TreatmentInfoDTO> response = patientTreatmentController.patientsUnderChemicalTreatment(anyString(), anyString());

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callPatientsUnderChemicalTreatmentThrowException() throws Exception {
		// given
		given(patientTreatmentService.patientsUnderChemicalTreatment(anyString(), anyString()))
				.willThrow(new ServiceException("Error: Too much pathologies"));

		// when
		List<TreatmentInfoDTO> response = patientTreatmentController.patientsUnderChemicalTreatment(anyString(), anyString());

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
		TreatmentInfoDTO treatmentInfoDTO = new TreatmentInfoDTO();
	
		treatmentInfoDTO.setCodeAct("ACT code");
		treatmentInfoDTO.setActIngredients("Act Ingredient");
		treatmentInfoDTO.setCount(1);		
	
		return treatmentInfoDTO;
	}

}

