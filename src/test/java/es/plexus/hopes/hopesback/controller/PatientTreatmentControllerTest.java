package es.plexus.hopes.hopesback.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import es.plexus.hopes.hopesback.service.PatientTreatmentService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientTreatmentControllerTest {

	@Mock
	private PatientTreatmentService patientTreatmentService;

	@InjectMocks
	private PatientTreatmentController patientTreatmentController;

	@Test
	public void callFindPatientsUnderTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findPatientsUnderTreatment(anyString(), anyString()))
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientTreatmentController.findPatientsUnderTreatment(anyString(), anyString());

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsUnderTreatmentThrowException() throws Exception {
		// given
		given(patientTreatmentService.findPatientsUnderTreatment(anyString(), anyString()))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Map<String, Long> response = patientTreatmentController.findPatientsUnderTreatment(anyString(), anyString());

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}
	
	@Test
	public void callFindInfoPatientsDosesShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findInfoPatientsDoses())
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientTreatmentController.findInfoPatientsDoses();

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}
		
	@Test(expected = ServiceException.class)
	public void callFindInfoPatientsDosesThrowException() throws Exception {
		// given
		given(patientTreatmentService.findInfoPatientsDoses())
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Map<String, Long> response = patientTreatmentController.findInfoPatientsDoses();

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}

	//Mocks
	private Map<String, Long> mockMapStringLong() {
		Map<String, Long> map = new HashMap<>();
		map.put("Type", 3L);
		return map;
	}
	
	
}

