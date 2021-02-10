package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import es.plexus.hopes.hopesback.service.RoleService;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.utils.MockUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;

import static org.mockito.BDDMockito.given;
// TODO: Refactorizar y descomentar estos test
@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientTreatmentControllerTest {

    @Autowired
    @Mock
    private RoleService roleService;

    @Mock
	private PatientTreatmentService patientTreatmentService;

	@InjectMocks
	private PatientTreatmentController patientTreatmentController;

	private final PageRequest mockPageRequest = PageRequest.of(1, 5, Sort.by("patient"));

	@Test
	public void fixMe(){
		Assert.assertTrue(true);
	}

	@Test
	public void callFindPatientsUnderTreatmentShouldBeStatusOk() {
		// given
        String token = MockUtils.mockToken();
        given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientsUnderTreatment("BIOLOGICO","psoriasis", MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapStringLong());

		// when
		Map<String, Long> response = patientTreatmentController.findPatientsUnderTreatment("BIOLOGICO","psoriasis", token);

		// then		
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindPatientsUnderTreatmentThrowException() throws ServiceException {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(patientTreatmentService.findPatientsUnderTreatment("BIOLOGICO","psoriasis", MockUtils.mockPathology()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		patientTreatmentController.findPatientsUnderTreatment("BIOLOGICO","psoriasis", token);
	}
	
	@Test
	public void callFindInfoPatientsDosesShouldBeStatusOk() {
		// given
		given(patientTreatmentService.findInfoPatientsDoses())
				.willReturn(MockUtils.mockMapStringLong());

		// when
		Map<String, Long> response = patientTreatmentController.findInfoPatientsDoses();

		// then		
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

   @Test(expected = ServiceException.class)
   public void callFindInfoPatientsDosesThrowException() throws ServiceException {
	   // given
	   given(patientTreatmentService.findInfoPatientsDoses())
			   .willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

	   // when
	   patientTreatmentController.findInfoPatientsDoses();
   }

   @Test
   public void callGetDetailPatientsUnderTreatmentShouldBeStatusOk() {
	   // given
	   String token = MockUtils.mockToken();
	   given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
	   given(patientTreatmentService.getDetailPatientsUnderTreatment("BIOLOGICO", "psoriasis", "SECUKINUMAB0",MockUtils.mockPageable(), MockUtils.mockPathology()))
			   .willReturn(MockUtils.getPageableGraphPatientDetail(mockPageRequest));

	   // when
	   Page<GraphPatientDetailDTO> response = patientTreatmentController.getDetailPatientsUnderTreatment("BIOLOGICO", "psoriasis", "SECUKINUMAB0", MockUtils.mockPageable(), token);

	   // then
	   Assert.assertNotNull(response);
	   Assert.assertFalse(response.isEmpty());
   }

   @Test(expected = ServiceException.class)
   public void callGetDetailPatientsUnderTreatmentThrowException()  throws ServiceException {
		// given
		String token = MockUtils.mockToken();
	   given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
	   given(patientTreatmentService.getDetailPatientsUnderTreatment("BIOLOGICO", "psoriasis", "SECUKINUMAB0", MockUtils.mockPageable(), MockUtils.mockPathology()))
			   .willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

	   // when
	   patientTreatmentController.getDetailPatientsUnderTreatment("BIOLOGICO", "psoriasis", "SECUKINUMAB0", MockUtils.mockPageable(), token);
   }

   @Test
   public void callGetDetailPatientsPerDosesShouldBeStatusOk() {
	   // given
	   String token = MockUtils.mockToken();
	   given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
	   given(patientTreatmentService.getDetailPatientsPerDoses("reg",MockUtils.mockPageable(), MockUtils.mockPathology()))
			   .willReturn(MockUtils.getPageableGraphPatientDetail(mockPageRequest));

	   // when
	   Page<GraphPatientDetailDTO> response = patientTreatmentController.getDetailPatientsPerDoses("reg",MockUtils.mockPageable(), token);

	   // then
	   Assert.assertNotNull(response);
	   Assert.assertFalse(response.isEmpty());
   }

   @Test(expected = ServiceException.class)
   public void callGetDetailPatientsPerDosesThrowException() throws ServiceException {
	   // given
	   String token = MockUtils.mockToken();
	   given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
	   given(patientTreatmentService.getDetailPatientsPerDoses("regimen", MockUtils.mockPageable(), MockUtils.mockPathology()))
			   .willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

	   // when
	   patientTreatmentController.getDetailPatientsPerDoses("regimen", MockUtils.mockPageable(), token);
   }

}

