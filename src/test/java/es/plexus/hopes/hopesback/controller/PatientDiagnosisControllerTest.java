package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@InjectMocks
	private PatientDiagnosisController patientDiagnosisController;

	private final PageRequest mockPageRequest = PageRequest.of(1, 5, Sort.by("patient"));
	@Test
	public void callFindPatientsDiagnosesByIndicationsShouldBeStatusOk() throws ServiceException {

		// given
		given(patientDiagnosisService.findPatientsByIndication())
				.willReturn(mockMapStringBooleanInteger());

		// when
		Map<String, Map<Boolean,Integer>> response = patientDiagnosisController.findPatientsDiagnosesByIndications();

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
	public void callFindPatientsByCieShouldBeStatusOk() throws ServiceException {

		// given
		given(patientDiagnosisService.findPatientsByCie(1L))
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientsDiagnosesByCie(anyLong());

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsByCieThrowException() throws ServiceException {
		// given
		given(patientDiagnosisService.findPatientsByCie(1L))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientsDiagnosesByCie(anyLong());

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

	@Test
	public void callFindGraphPatientsDetailsByIndicationShouldBeStatusOk() {

		final PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));

		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", pageRequest))
				.willReturn(getPageableGraphPatientDetail(pageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", pageRequest);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByIndicationThrowException() throws ServiceException {
		final PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id"));
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByIndication(anyString(), any(Pageable.class)))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", pageRequest);

	}

	@Test
	public void callFindGraphPatientsDetailsListByIndicationShouldBeStatusOk() {

		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByIndication("Psoriasis pustulosa"))
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

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

		final PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id"));

		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L, pageRequest))
				.willReturn(getPageableGraphPatientDetail(pageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByCie("Psoriasis pustulosa", anyLong(), pageRequest);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByByCieThrowException() throws ServiceException {
		final PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id"));
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByCie(anyString(), anyLong(), any(Pageable.class)))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L, pageRequest);

	}

	@Test
	public void callFindGraphPatientsDetailsListByByCieShouldBeStatusOk() {

		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L))
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

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
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByTypeTreatment("Psoriasis pustulosa");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByTypeTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findGraphPatientsDetailsByTypeTreatment("Psoriasis", mockPageRequest))
				.willReturn(getPageableGraphPatientDetail(mockPageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByTypeTreatment("Psoriasis", mockPageRequest);

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
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatment("Psoriasis pustulosa", "reason");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByEndCauseBiologicTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatment("Psoriasis", "reason", mockPageRequest))
				.willReturn(getPageableGraphPatientDetail(mockPageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatment("Psoriasis", "reason", mockPageRequest);

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
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears("Psoriasis pustulosa", "reason", 2);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByEndCauseBiologicTreatmentInLastYearsShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears("Psoriasis", "reason", 5, mockPageRequest))
				.willReturn(getPageableGraphPatientDetail(mockPageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByEndCauseBiologicTreatmentInLastYears("Psoriasis", "reason", 5, mockPageRequest);

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
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges(2))
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByNumberChanges( 2);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByNumberChangesShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges(2, mockPageRequest))
				.willReturn(getPageableGraphPatientDetail(mockPageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByNumberChanges(2, mockPageRequest);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByNumberChangesThrowException() throws ServiceException {
		// given
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges(anyInt()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByNumberChanges( 2);
	}

	@Test
	public void callFindGraphPatientsDetailsByCombinedTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findGraphPatientsDetailsByCombiendTreatment("Combined"))
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByCombinedTreatment("Combined");

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByCombinedTreatmentShouldBeStatusOk() {

		// given
		given(patientTreatmentService.findGraphPatientsDetailsByCombiendTreatment("Combined", mockPageRequest))
				.willReturn(getPageableGraphPatientDetail(mockPageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByCombinedTreatment("Combined", mockPageRequest);

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

	//Mocks mockMap
	private Map<String, Long> mockMapStringLong() {
		Map<String, Long> map = new HashMap<>();
		map.put("Type", 3L);
		return map;
	}

	//Mocks mockMap
	private Map<String, Map<Boolean,Integer>> mockMapStringBooleanInteger() {
		Map<String, Map<Boolean,Integer>> map = new HashMap<>();
		Map<Boolean, Integer> mapBooleanInteger = new HashMap<>();
		mapBooleanInteger.put(true, 3);
		map.put("Type", mapBooleanInteger);
		return map;
	}

	//Mocks mockMap
	private Map<Long, Integer> mockMapLongInteger() {
		Map<Long, Integer> map = new HashMap<>();
		map.put(3L, 1);
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

