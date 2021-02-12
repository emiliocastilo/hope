package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.service.PatientDiagnosisService;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import es.plexus.hopes.hopesback.service.RoleService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.utils.MockUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientDiagnosisControllerTest {

	@Mock
	private PatientDiagnosisService patientDiagnosisService;

	@Mock
	private PatientTreatmentService patientTreatmentService;

	@Autowired
	@Mock
	private RoleService roleService;

	@InjectMocks
	private PatientDiagnosisController patientDiagnosisController;

	private final String token =  MockUtils.mockToken();

	@Test
	public void callFindPatientsDiagnosesByIndicationsShouldBeStatusOk() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientDiagnosisService.findPatientsByIndicationAndPathology(MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapStringBooleanInteger());

		// when
		Map<String, Map<Boolean,Integer>> response = patientDiagnosisController.findPatientsDiagnosesByIndications(token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsDiagnosesByIndicationsThrowException() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientDiagnosisService.findPatientsByIndicationAndPathology(MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientsDiagnosesByIndications(token);
	}

	@Test
	public void callFindPatientsByCieShouldBeStatusOk() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientDiagnosisService.findPatientsByCieAndPathology(1L,MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientsDiagnosesByCie(1L, token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsByCieThrowException() {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientDiagnosisService.findPatientsByCieAndPathology(1L, MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientsDiagnosesByCie(1L,token);
	}

	@Test
	public void callFindPatientTreatmentByTreatmentShouldBeStatusOk() {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByTreatmentAndPathology(MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByTreatment(token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByTreatmentThrowException() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByTreatmentAndPathology(MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByTreatment(token);
	}

	@Test
	public void callFindPatientTreatmentByCombinedTreatmentShouldBeStatusOk() {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByCombinedTreatmentAndPathology(MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByCombinedTreatment(token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByCombinedTreatmentThrowException() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByCombinedTreatmentAndPathology(MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByCombinedTreatment(token);
	}

	@Test
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentShouldBeStatusOk() {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathology("CHANGE",MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatment("CHANGE", token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentThrowException() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathology("SUSPENSION", MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatment("SUSPENSION", token);
	}

	@Test
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentInLast5YearsShouldBeStatusOk() {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathologyInLast5Years("CHANGE",MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapStringLong());

		// when
		Map<String, Long> response =
				patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years("CHANGE", token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentInLast5YearsThrowException() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathologyInLast5Years("SUSPENSION",MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years("SUSPENSION", token);
	}

	@Test
	public void callFindPatientTreatmentByNumberChangesOfBiologicTreatmentShouldBeStatusOk() {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment(MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapStringLong());

		// when
		Map<String, Long> response =
				patientDiagnosisController.findPatientTreatmentByNumberChangesOfBiologicTreatment(token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByNumberChangesOfBiologicTreatmentThrowException() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment(MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByNumberChangesOfBiologicTreatment(token);
	}

	@Test
	public void callFindGraphPatientsDetailsByIndicationShouldBeStatusOk() {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", MockUtils.mockPageRequest()))
				.willReturn(MockUtils.getPageableGraphPatientDetail(MockUtils.mockPageRequest()));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", MockUtils.mockPageRequest());

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByIndicationThrowException() throws ServiceException {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByIndication(anyString(), any(Pageable.class)))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", MockUtils.mockPageRequest());
	}

	@Test
	public void callFindGraphPatientsDetailsListByIndicationShouldBeStatusOk() {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByIndication("Psoriasis pustulosa"))
				.willReturn(Collections.singletonList(MockUtils.mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByIndication("Psoriasis pustulosa");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsListByIndicationThrowException() throws ServiceException {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByIndication(anyString()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByIndication("Psoriasis pustulosa");
	}

	@Test
	public void callFindGraphPatientsDetailsByCieShouldBeStatusOk() {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L, MockUtils.mockPageRequest()))
				.willReturn(MockUtils.getPageableGraphPatientDetail(MockUtils.mockPageRequest()));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L, MockUtils.mockPageRequest());

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByByCieThrowException() throws ServiceException {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByCie(anyString(), anyLong(), any(Pageable.class)))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L, MockUtils.mockPageRequest());
	}

	@Test
	public void callFindGraphPatientsDetailsListByByCieShouldBeStatusOk() {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L))
				.willReturn(Collections.singletonList(MockUtils.mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsListByByCieThrowException() throws ServiceException {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByCie(anyString(), anyLong()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L);
	}

	@Test
	public void callFindGraphPatientsDetailsByTypeTreatmentShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByTypeTreatment("Psoriasis pustulosa"))
				.willReturn(Collections.singletonList(MockUtils.mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByTypeTreatment("Psoriasis pustulosa");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByTypeTreatmentShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByTypeTreatment("Psoriasis", MockUtils.mockPageRequest()))
				.willReturn(MockUtils.getPageableGraphPatientDetail(MockUtils.mockPageRequest()));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByTypeTreatment("Psoriasis", MockUtils.mockPageRequest());

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByTypeTreatmentThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByTypeTreatment(anyString()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByTypeTreatment("Psoriasis pustulosa");
	}

	@Test
	public void callFindGraphPatientsDetailsByEndCauseBiologicTreatmentShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatment("Psoriasis pustulosa", "reason"))
				.willReturn(Collections.singletonList(MockUtils.mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatment("Psoriasis pustulosa", "reason");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByEndCauseBiologicTreatmentShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatment("Psoriasis", "reason", MockUtils.mockPageRequest()))
				.willReturn(MockUtils.getPageableGraphPatientDetail(MockUtils.mockPageRequest()));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatment("Psoriasis", "reason", MockUtils.mockPageRequest());

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByEndCauseBiologicTreatmentThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatment(anyString(), anyString()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatment("Psoriasis pustulosa", "reason");
	}

	@Test
	public void callFindGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYearsShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears("Psoriasis pustulosa", "reason", 2))
				.willReturn(Collections.singletonList(MockUtils.mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears("Psoriasis pustulosa", "reason", 2);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByEndCauseBiologicTreatmentInLastYearsShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears("Psoriasis", "reason", 5, MockUtils.mockPageRequest()))
				.willReturn(MockUtils.getPageableGraphPatientDetail(MockUtils.mockPageRequest()));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears("Psoriasis", "reason", 5, MockUtils.mockPageRequest());

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYearsThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears(anyString(), anyString(), anyInt()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears("Psoriasis pustulosa", "reason", 2);
	}

	@Test
	public void callFindGraphPatientsDetailsByNumberChangesShouldBeStatusOk() {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges("2",  MockUtils.mockPathology()))
				.willReturn(Collections.singletonList(MockUtils.mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByNumberChanges( "2", token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByNumberChangesShouldBeStatusOk() {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges("2", MockUtils.mockPageable(), MockUtils.mockPathology()))
				.willReturn(MockUtils.getPageableGraphPatientDetail(MockUtils.mockPageRequest()));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByNumberChanges("2", MockUtils.mockPageable(),  token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByNumberChangesThrowException() throws ServiceException {
		// given
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges("2", MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByNumberChanges( "2", token);
	}

	@Test
	public void callFindGraphPatientsDetailsByCombinedTreatmentShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByCombiendTreatment("Combined"))
				.willReturn(Collections.singletonList(MockUtils.mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByCombinedTreatment("Combined");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByCombinedTreatmentShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByCombiendTreatment("Combined", MockUtils.mockPageRequest()))
				.willReturn(MockUtils.getPageableGraphPatientDetail(MockUtils.mockPageRequest()));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByCombinedTreatment("Combined", MockUtils.mockPageRequest());

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByCombinedTreatmentThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByCombiendTreatment(anyString()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByCombinedTreatment("Combined");
	}

}

