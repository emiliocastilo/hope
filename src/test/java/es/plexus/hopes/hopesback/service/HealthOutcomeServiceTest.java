package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.HealthOutcomeRepository;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.Silent.class)
public class HealthOutcomeServiceTest {

	@Mock
	private HealthOutcomeRepository healthOutcomeRepository;

	@Mock
	private PatientRepository patientRepository;

	@InjectMocks
	private HealthOutcomeService healthOutcomeService;



	@Test
	public void callFindResultsByTypesShouldBeStatusOk() {

		// given
		given(healthOutcomeRepository.findResultsByTypes(anyString()))
				.willReturn(Collections.singletonList(mockHealthOutcome()));

		// when
		Map<String, Long> response = healthOutcomeService.findResultsByTypes(anyString());

		// then		
		Assert.assertNotNull(response);
	}
		

	@Test
	public void callDetailsResultsShouldBeStatusOk() {

		// given
		final PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("patient"));
		given(patientRepository.getDetailsResultsByType(anyString()
				, anyString()))
				.willReturn(Collections.singletonList(mockPatient()));

		// when
		Page<GraphPatientDetailDTO> response = healthOutcomeService.getDetailsResultsByType("PASI", "PASI", pageRequest);

		// then		
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	private Patient mockPatient() {
		Patient patient = new Patient();
		patient.setHealthOutcomes(Collections.singletonList(new HealthOutcome()));
		patient.setName("name");
		patient.setNhc("nhc");
		patient.setDiagnoses(Collections.singletonList(new PatientDiagnose()));
		return patient;
	}

	@Test(expected = ServiceException.class)
	public void callDetailsResultsThrowException() throws ServiceException {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
		given(patientRepository.getDetailsResultsByType(anyString(),  anyString()))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		Page<GraphPatientDetailDTO> response = healthOutcomeService.getDetailsResultsByType("PASI", "", pageRequest);

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response);
		Assert.assertNull(response);
	}


	@Test(expected = ServiceException.class)
	public void callDetailsResultsExportThrowException() throws ServiceException {
		// given
		given(patientRepository.getDetailsResultsByType(anyString(),  anyString()))
				.willThrow(new ServiceException("Error: No contled error"));

		// when
		List<GraphPatientDetailDTO> response = healthOutcomeService.getDetailsResultsByType("PASI", "");

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response);
		Assert.assertNull(response);
	}

	@Test
	public void callGetAllPatientsIdShouldBeStatusOk() {

		// given
		given(healthOutcomeRepository.getAllPatientsId()).willReturn(Collections.singletonList(1L));

		// when
		List<Long> response = healthOutcomeService.getAllPatientsId();

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	//Mocks
	private HealthOutcome mockHealthOutcome(){
		HealthOutcome healthOutcome = new HealthOutcome();
		healthOutcome.setPatient(new Patient());
		healthOutcome.setDate(LocalDateTime.now());
		healthOutcome.setId(1L);
		healthOutcome.setIndexType("PASI");
		healthOutcome.setResult("Result");
		healthOutcome.setValue(new BigDecimal(0.0));
		return healthOutcome;
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

