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

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
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
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patien<t"));
		given(healthOutcomeService.detailsResults(anyString(), any(Pageable.class)))
				.willReturn(getPageableDetailResult(pageRequest));

		// when
		Page<DetailGraphDTO> response = healthOutcomeController.detailsResults("PASI", pageRequest);

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}
	
	@Test(expected = ServiceException.class)
	public void callDetailsResultsThrowException() throws Exception {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(healthOutcomeService.detailsResults(anyString(), any(Pageable.class)))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Page<DetailGraphDTO> response = healthOutcomeController.detailsResults("PASI", pageRequest);

		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
		Assert.assertNull(response);
	}
	
	
	//Mocks
	private Map<String, Long> mockMapStringLong() {
		Map<String, Long> map = new HashMap<>();
		map.put("Type", 3L);
		return map;
	}
	
	private DetailGraphDTO mockDetailGraphDTO() {
		DetailGraphDTO detailGraphDTO = new DetailGraphDTO();
		detailGraphDTO.setNhc("1234");
		detailGraphDTO.setSip("1234");
		detailGraphDTO.setPatient("Antonio Diaz Alonso");
		detailGraphDTO.setIndication("Indicacion1");
		detailGraphDTO.setDiagnostig("Diagnostio1");
		detailGraphDTO.setTreatment("Tratamiento1");
		detailGraphDTO.setPasi("3");
		detailGraphDTO.setDatePasi(LocalDateTime.now());
		return detailGraphDTO;
	}
	
	private PageImpl<DetailGraphDTO> getPageableDetailResult(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockDetailGraphDTO()), pageRequest, 1);
	}
}

