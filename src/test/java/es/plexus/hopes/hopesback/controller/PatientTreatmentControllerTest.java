package es.plexus.hopes.hopesback.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
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
	
	@Test
	public void callGetDetailPatientsUnderTreatmentShouldBeStatusOk() {

		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(patientTreatmentService.getDetailPatientsUnderTreatment(anyString(), anyString(), any(Pageable.class)))
				.willReturn(getPageableGraphPatientDetail(pageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientTreatmentController.getDetailPatientsUnderTreatment("BIOLOGICO", "psoriasis", pageRequest);

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}
	
	@Test(expected = ServiceException.class)
	public void callGetDetailPatientsUnderTreatmentThrowException() throws Exception {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(patientTreatmentService.getDetailPatientsUnderTreatment(anyString(), anyString(), any(Pageable.class)))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Page<GraphPatientDetailDTO> response = patientTreatmentController.getDetailPatientsUnderTreatment("BIOLOGICO", "psoriasis", pageRequest);

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}
	
	@Test
	public void callGetDetailPatientsPerDosesShouldBeStatusOk() {

		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(patientTreatmentService.getDetailPatientsPerDoses(any(Pageable.class)))
				.willReturn(getPageableGraphPatientDetail(pageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientTreatmentController.getDetailPatientsPerDoses(pageRequest);

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}
	
	@Test(expected = ServiceException.class)
	public void callGetDetailPatientsPerDosesThrowException() throws Exception {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(patientTreatmentService.getDetailPatientsPerDoses(any(Pageable.class)))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Page<GraphPatientDetailDTO> response = patientTreatmentController.getDetailPatientsPerDoses(pageRequest);

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}
	
	private GraphPatientDetailDTO mockGraphPatientDetailsDTO() {
		GraphPatientDetailDTO graphPatientDetailDTO =
				new GraphPatientDetailDTO(1L,
						"NOHC0001",
						"HC0001",
						"Nombre completo",
						"Indication",
						"Diagnose CIE 9",
						"Diagnose cie 10",
						"Treatment",
						"PASI Result",
						LocalDateTime.now(),
						"DLQI Result",
						LocalDateTime.now());

		return graphPatientDetailDTO;
	}

	private PageImpl<GraphPatientDetailDTO> getPageableGraphPatientDetail(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockGraphPatientDetailsDTO()), pageRequest, 1);
	}
}

