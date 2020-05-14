package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.CieInfoDTO;
import es.plexus.hopes.hopesback.controller.model.GroupingFieldTreatmentInfoDTO;
import es.plexus.hopes.hopesback.controller.model.IndicationInfoDTO;
import es.plexus.hopes.hopesback.controller.model.PatientTreatmentDTO;
import es.plexus.hopes.hopesback.service.PatientDiagnosisService;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientDiagnosisControllerTest {

	@Mock
	private PatientDiagnosisService patientDiagnosisService;

	@Mock
	private PatientTreatmentService patientTreatmentService;

	@InjectMocks
	private PatientDiagnosisController patientDiagnosisController;

	@Test
	public void callFindPatientsDiagnosesByIndicationsShouldBeStatusOk() throws ServiceException {

		// given
		given(patientDiagnosisService.findPatientsByIndication())
				.willReturn(Collections.singletonList(mockIndicationInfoDTO()));

		// when
		List<IndicationInfoDTO> response = patientDiagnosisController.findPatientsDiagnosesByIndications();

		// then		
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsDiagnosesByIndicationsThrowException() throws ServiceException {
		// given
		given(patientDiagnosisService.findPatientsByIndication())
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientsDiagnosesByIndications();

	}

	@Test
	public void callFindPatientsByCie9ShouldBeStatusOk() throws ServiceException {

		// given
		given(patientDiagnosisService.findPatientsByCie9())
				.willReturn(Collections.singletonList(mockCieInfoDTO()));

		// when
		List<CieInfoDTO> response = patientDiagnosisController.findPatientsDiagnosesByCie9();

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsByCie9ThrowException() throws ServiceException {
		// given
		given(patientDiagnosisService.findPatientsByCie9())
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientsDiagnosesByCie9();

	}

	@Test
	public void callFindPatientsByCie10ShouldBeStatusOk() throws ServiceException {

		// given
		given(patientDiagnosisService.findPatientsByCie10())
				.willReturn(Collections.singletonList(mockCieInfoDTO()));

		// when
		List<CieInfoDTO> response = patientDiagnosisController.findPatientsDiagnosesByCie10();

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsByCie10ThrowException() throws ServiceException {
		// given
		given(patientDiagnosisService.findPatientsByCie10())
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientsDiagnosesByCie10();
	}

	@Test
	public void callFindPatientTreatmentByTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findPatientTreatmentByTreatment())
				.willReturn(Collections.singletonList(mockGroupingFieldTreatmentInfoDTO()));

		// when
		List<GroupingFieldTreatmentInfoDTO> response = patientDiagnosisController.findPatientTreatmentByTreatment();

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByTreatmentThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findPatientTreatmentByTreatment())
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByTreatment();

	}

	@Test
	public void callFindPatientTreatmentByCombinedTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findPatientTreatmentByCombinedTreatment())
				.willReturn(Collections.singletonList(mockGroupingFieldTreatmentInfoDTO()));

		// when
		List<GroupingFieldTreatmentInfoDTO> response = patientDiagnosisController.findPatientTreatmentByCombinedTreatment();

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByCombinedTreatmentThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findPatientTreatmentByCombinedTreatment())
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByCombinedTreatment();

	}

	@Test
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatment("CHANGE"))
				.willReturn(Collections.singletonList(mockGroupingFieldTreatmentInfoDTO()));

		// when
		List<GroupingFieldTreatmentInfoDTO> response = patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatment("CHANGE");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatment("SUSPENSION"))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatment("SUSPENSION");
	}

	@Test
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentInLast5YearsShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years("CHANGE"))
				.willReturn(Collections.singletonList(mockGroupingFieldTreatmentInfoDTO()));

		// when
		List<GroupingFieldTreatmentInfoDTO> response =
				patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years("CHANGE");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentInLast5YearsThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years("SUSPENSION"))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years("SUSPENSION");
	}

	@Test
	public void callFindPatientTreatmentByNumberChangesOfBiologicTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment())
				.willReturn(Collections.singletonList(mockPatientTreatmentDTO()));

		// when
		List<PatientTreatmentDTO> response =
				patientDiagnosisController.findPatientTreatmentByNumberChangesOfBiologicTreatment();

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByNumberChangesOfBiologicTreatmentThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment())
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByNumberChangesOfBiologicTreatment();

	}


	//Mocks IndicationInfoDTO
	private IndicationInfoDTO mockIndicationInfoDTO() {
		IndicationInfoDTO indicationInfoDTO = new IndicationInfoDTO();
	
		indicationInfoDTO.setIndication("Psoriasis en placas");
		indicationInfoDTO.setNumberPatients(3L);

		return indicationInfoDTO;
	}

	//Mocks CieInfoDTO
	private CieInfoDTO mockCieInfoDTO() {
		CieInfoDTO indicationInfoDTO = new CieInfoDTO();

		indicationInfoDTO.setCode("A00");
		indicationInfoDTO.setDescription("Enfermedades infecciosas intestinales");
		indicationInfoDTO.setNumberPatients(3L);

		return indicationInfoDTO;
	}

	//Mocks GroupingFieldTreatmentInfoDTO
	private GroupingFieldTreatmentInfoDTO mockGroupingFieldTreatmentInfoDTO() {
		GroupingFieldTreatmentInfoDTO groupingFieldTreatmentInfoDTO = new GroupingFieldTreatmentInfoDTO();

		groupingFieldTreatmentInfoDTO.setGroupingField("Type");
		groupingFieldTreatmentInfoDTO.setNumberPatients(3L);

		return groupingFieldTreatmentInfoDTO;
	}

	//Mocks PatientTreatmentDTO
	private PatientTreatmentDTO mockPatientTreatmentDTO() {
		PatientTreatmentDTO patientTreatmentDTO = new PatientTreatmentDTO();

		patientTreatmentDTO.setActive(true);
		patientTreatmentDTO.setNumberPatients(3L);

		return patientTreatmentDTO;
	}
}
