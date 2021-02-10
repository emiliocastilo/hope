package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.HealthOutcomeRepository;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.utils.MockUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
// TODO: Refactorizar y descomentar estos test
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
				.willReturn(Collections.singletonList(MockUtils.mockHealthOutcome()));

		// when
		Map<String, Long> response = healthOutcomeService.findResultsByTypes(anyString());

		// then		
		Assert.assertNotNull(response);
	}


    @Test
    public void callDetailsResultsShouldBeStatusOk() {
        // given
        given(patientRepository.getDetailsResultsByType("PASI", "PASI"))
                .willReturn(Collections.singletonList(MockUtils.mockPatient()));

        // when
        Page<GraphPatientDetailDTO> response = healthOutcomeService.getDetailsResultsByType("PASI", "PASI", MockUtils.mockPageRequest());

        // then
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
    }

    @Test(expected = ServiceException.class)
    public void callDetailsResultsThrowException() throws ServiceException {
        // given
        given(patientRepository.getDetailsResultsByType("PASI", ""))
                .willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

        // when
        Page<GraphPatientDetailDTO> response = healthOutcomeService.getDetailsResultsByType("PASI", "", MockUtils.mockPageRequest());

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response);
        Assert.assertNull(response);
    }


    @Test(expected = ServiceException.class)
    public void callDetailsResultsExportThrowException() throws ServiceException {
        // given
        given(patientRepository.getDetailsResultsByType("PASI", ""))
                .willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

        // when
        List<GraphPatientDetailDTO> response = healthOutcomeService.getDetailsResultsByType("PASI", "");

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response);
        Assert.assertNull(response);
    }

    @Test
    public void callGetAllPatientsIdShouldBeStatusOk() {
        // given
        given(healthOutcomeRepository.getAllPatientsId())
                .willReturn(Collections.singletonList(MockUtils.mockPatient()));

        // when
        List<Long> response = healthOutcomeService.getAllPatientsIdByPathology(MockUtils.mockPathology());

        // then
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
    }

    //Mocks



/*
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

    private PageImpl<Patient> mockPagePatient_(PageRequest pageRequest) {
        return new PageImpl<>(Collections.singletonList(MockUtils.mockPatient()), pageRequest, 1);
    }
*/
    //private final PageRequest mockPageRequest_() {
    //    return PageRequest.of(0, 5, Sort.by("patient"));
    //}




    @Test
    public void fixMe(){
        Assert.assertTrue(true);
    }
}

