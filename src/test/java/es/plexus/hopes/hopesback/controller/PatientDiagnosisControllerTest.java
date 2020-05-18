package es.plexus.hopes.hopesback.controller;

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

import java.util.HashMap;
import java.util.Map;

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
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientsDiagnosesByIndications();

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
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientsDiagnosesByCie9();

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
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientsDiagnosesByCie10();

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
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByTreatment();

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
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByCombinedTreatment();

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
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatment("CHANGE");

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
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response =
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
				.willReturn(mockMapLongInteger());

		// when
		Map<Long, Integer> response =
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

	//Mocks mockMap
	private Map<String, Long> mockMapStringLong() {
		Map<String, Long> map = new HashMap<>();
		map.put("Type", 3L);
		return map;
	}

	//Mocks mockMap
	private Map<Long, Integer> mockMapLongInteger() {
		Map<Long, Integer> map = new HashMap<>();
		map.put(3L, 1);
		return map;
	}
}

