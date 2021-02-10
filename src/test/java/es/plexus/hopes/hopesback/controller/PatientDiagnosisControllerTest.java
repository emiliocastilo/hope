package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import es.plexus.hopes.hopesback.repository.model.Role;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
// TODO: Refactorizar y descomentar estos test
@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientDiagnosisControllerTest {

    @Test
    public void fixMe(){
        Assert.assertTrue(true);
    }

	@Mock
	private PatientDiagnosisService patientDiagnosisService;

	@Mock
	private PatientTreatmentService patientTreatmentService;

	@Autowired
	@Mock
	private RoleService roleService;

	//@Mock
	//private RoleRepository roleRepository;

	@InjectMocks
	private PatientDiagnosisController patientDiagnosisController;

	private final PageRequest mockPageRequest = PageRequest.of(0, 5, Sort.by("patient"));

	@Test
	public void callFindPatientsDiagnosesByIndicationsShouldBeStatusOk() throws ServiceException {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientDiagnosisService.findPatientsByIndicationAndPathology(mockPathology()))
				.willReturn(mockMapStringBooleanInteger());

		//given(roleRepository.findByCode("ROLE_ADMIN")).willReturn(Optional.of(mockRole()));
		//Pathology pathology = roleService.getPathologyByRoleSelected(token);

		// when
		Map<String, Map<Boolean,Integer>> response = patientDiagnosisController.findPatientsDiagnosesByIndications(token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsDiagnosesByIndicationsThrowException() throws ServiceException {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientDiagnosisService.findPatientsByIndicationAndPathology(mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		Map<String, Map<Boolean,Integer>> response = null;
		//try {
			response = patientDiagnosisController.findPatientsDiagnosesByIndications(token);
			//Assert.fail();
		//} catch (ServiceException e) {

			//Assert.assertTrue(e.getErrorCode().equals("IE_001"));
		//}

	}

	@Test
	public void callFindPatientsByCieShouldBeStatusOk() throws ServiceException {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientDiagnosisService.findPatientsByCieAndPathology(1L,mockPathology()))
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientsDiagnosesByCie(1L, token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());

	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsByCieThrowException() {
	// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientDiagnosisService.findPatientsByCieAndPathology(1L, mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientsDiagnosesByCie(1L,token);

	}

	@Test
	public void callFindPatientTreatmentByTreatmentShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByTreatmentAndPathology(mockPathology()))
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByTreatment(token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByTreatmentThrowException() throws ServiceException {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByTreatmentAndPathology(mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByTreatment(token);

	}

	@Test
	public void callFindPatientTreatmentByCombinedTreatmentShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByCombinedTreatmentAndPathology(mockPathology()))
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByCombinedTreatment(token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByCombinedTreatmentThrowException() throws ServiceException {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByCombinedTreatmentAndPathology(mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByCombinedTreatment(token);

	}

	@Test
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathology("CHANGE",mockPathology()))
				.willReturn(mockMapStringLong());

		// when
		Map<String, Long> response = patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatment("CHANGE", token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentThrowException() throws ServiceException {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathology("SUSPENSION", mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatment("SUSPENSION", token);
	}

	@Test
	public void callFindPatientTreatmentByEndCauseBiologicTreatmentInLast5YearsShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathologyInLast5Years("CHANGE",mockPathology()))
				.willReturn(mockMapStringLong());

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
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByEndCauseBiologicTreatmentAndPathologyInLast5Years("SUSPENSION",mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years("SUSPENSION", token);
	}

	@Test
	public void callFindPatientTreatmentByNumberChangesOfBiologicTreatmentShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment(mockPathology()))
				.willReturn(mockMapStringLong());

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
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findPatientTreatmentByNumberChangesOfBiologicTreatment(mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findPatientTreatmentByNumberChangesOfBiologicTreatment(token);

	}

	@Test
	public void callFindGraphPatientsDetailsByIndicationShouldBeStatusOk() {
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", mockPageRequest))
				.willReturn(getPageableGraphPatientDetail(mockPageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", mockPageRequest);

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
		patientDiagnosisController.findGraphPatientsDetailsByIndication("Psoriasis pustulosa", mockPageRequest);

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
		// given
		given(patientDiagnosisService.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L, mockPageRequest))
				.willReturn(getPageableGraphPatientDetail(mockPageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L, mockPageRequest);

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
		patientDiagnosisController.findGraphPatientsDetailsByCie("Psoriasis pustulosa", 1L, mockPageRequest);

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
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges(2,  mockPathology()))
				.willReturn(Collections.singletonList(mockGraphPatientDetailsDTO()));

		// when
		List<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByNumberChanges( 2, token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindGraphPatientsDetailsPageByNumberChangesShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges(2, mockPageable(), mockPathology()))
				.willReturn(getPageableGraphPatientDetail(mockPageRequest));

		// when
		Page<GraphPatientDetailDTO> response = patientDiagnosisController.findGraphPatientsDetailsByNumberChanges(2, mockPageable(),  token);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindGraphPatientsDetailsByNumberChangesThrowException() throws ServiceException {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(mockPathology());
		given(patientTreatmentService.findGraphPatientsDetailsByNumberChanges(2, mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientDiagnosisController.findGraphPatientsDetailsByNumberChanges( 2, token);
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
		Map<String, Long> map = new HashMap();
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

	private Role mockRole() {

		final Role role = new Role();
		role.setId(1L);
		role.setName("Administrador_Dermatología");
		role.setCode("ROLE_ADMIN");
		role.setDescription("Rol_administrador. Tiene el máximo nivel de acceso a la aplicación");
		role.setPathology(mockPathology());
		return role;
	}

	public static Pathology mockPathology(){
		Pathology pathology = new Pathology();
		pathology.setId(1L);
		pathology.setName("Dermatología");
		pathology.setDescription("Patologia Dermatología");
		return pathology;
	}
	public static Pageable mockPageable(){
		return PageRequest.of(1, 5);
	}
}

