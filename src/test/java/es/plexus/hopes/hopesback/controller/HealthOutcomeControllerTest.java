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
import es.plexus.hopes.hopesback.service.HealthOutcomeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class HealthOutcomeControllerTest {

	@Mock
	private HealthOutcomeService healthOutcomeService;

	@InjectMocks
	private HealthOutcomeController healthOutcomeController;


	@Test
	public void callFindResultsByTypesShouldBeStatusOk() {

		// given
		given(healthOutcomeService.findResultsByTypes(anyString()))
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = healthOutcomeController.findResultsByTypes(anyString());

		// then		
		Assert.assertNotNull(response);
	}
		
	@Test(expected = ServiceException.class)
	public void callFindResultsByTypesThrowException() throws Exception {
		// given
		given(healthOutcomeService.findResultsByTypes(anyString()))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Map<String, Long> response = healthOutcomeController.findResultsByTypes(anyString());

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}
	
	@Test
	public void callDetailsResultsShouldBeStatusOk() {

		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(healthOutcomeService.getDetailsResultsByType(anyString(), any(Pageable.class)))
				.willReturn(getPageableGraphPatientDetail(pageRequest));

		// when
		Page<GraphPatientDetailDTO> response = healthOutcomeController.getDetailsResultsByType("PASI", pageRequest);

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}
	
	@Test(expected = ServiceException.class)
	public void callDetailsResultsThrowException() throws Exception {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(healthOutcomeService.getDetailsResultsByType(anyString(), any(Pageable.class)))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Page<GraphPatientDetailDTO> response = healthOutcomeController.getDetailsResultsByType("PASI", pageRequest);

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}
	
	
	//Mocks
	private Map<String, Long> mockMapStringLong() {
		Map<String, Long> map = new HashMap<>();
		map.put("Type", 3L);
		return map;
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

