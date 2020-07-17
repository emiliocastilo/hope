package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.service.HealthOutcomeService;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

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
	public void callFindResultsByTypesThrowException() throws ServiceException {
		// given
		given(healthOutcomeService.findResultsByTypes(anyString()))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Map<String, Long> response = healthOutcomeController.findResultsByTypes(anyString());

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response);
		Assert.assertNull(response);
	}
	
	@Test
	public void callDetailsResultsShouldBeStatusOk() {

		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(healthOutcomeService.getDetailsResultsByType(anyString(), anyString(), any(Pageable.class)))
				.willReturn(getPageableGraphPatientDetail(pageRequest));

		// when
		Page<GraphPatientDetailDTO> response = healthOutcomeController.getDetailsResultsByType("PASI", "PASI", pageRequest);

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}
	
	@Test(expected = ServiceException.class)
	public void callDetailsResultsThrowException() throws ServiceException {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(healthOutcomeService.getDetailsResultsByType(anyString(),  anyString(), any(Pageable.class)))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Page<GraphPatientDetailDTO> response = healthOutcomeController.getDetailsResultsByType("PASI", "", pageRequest);

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response);
		Assert.assertNull(response);
	}

	public void callDetailsResultsExportShouldBeStatusOk() {
		// given
		given(healthOutcomeService.getDetailsResultsByType(anyString(), anyString()))
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = healthOutcomeController.getDetailsResultsByType(anyString(), anyString());

		Assert.assertEquals(HttpStatus.OK,response);
		Assert.assertNull(response);
	}

	@Test(expected = ServiceException.class)
	public void callDetailsResultsExportThrowException() throws ServiceException {
		// given
		given(healthOutcomeService.getDetailsResultsByType(anyString(),  anyString()))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		List<GraphPatientDetailDTO> response = healthOutcomeController.getDetailsResultsByType("PASI", "");

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response);
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
				new GraphPatientDetailDTO();

		graphPatientDetailDTO.setId(1L);
		graphPatientDetailDTO.setNhc("NOHC0001");
		graphPatientDetailDTO.setHealthCard("HC0001");
		graphPatientDetailDTO.setFullName("Nombre completo");
		graphPatientDetailDTO.setPrincipalIndication("Indication");
		graphPatientDetailDTO.setPrincipalDiagnose("Diagnose CIE");
		graphPatientDetailDTO.setTreatment("Treatment");
		graphPatientDetailDTO.setPasi("PASI Result");
		graphPatientDetailDTO.setPasiDate(LocalDateTime.now());
		graphPatientDetailDTO.setDlqi("DLQI Result");
		graphPatientDetailDTO.setDlqiDate(LocalDateTime.now());

		return graphPatientDetailDTO;
	}

	private PageImpl<GraphPatientDetailDTO> getPageableGraphPatientDetail(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockGraphPatientDetailsDTO()), pageRequest, 1);
	}
}

