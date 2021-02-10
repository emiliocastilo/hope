package es.plexus.hopes.hopesback.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.service.DispensationDetailService;
// TODO: Refactorizar y descomentar estos test
@RunWith(MockitoJUnitRunner.Silent.class)
public class DispensationDetailControllerTest {

	@Mock
	private DispensationDetailService dispensationDetailService;

	@InjectMocks
	private DispensationDetailController dispensationDetailController;

	@Autowired
	@Mock
	private RoleService roleService;

	@Test
	public void fixMe(){
		Assert.assertTrue(true);
	}

	@Test
	public void callCreateShouldBeStatusOk() {
		// given
		given(dispensationDetailService.save(new DispensationDetailDTO()))
				.willReturn(MockUtils.mockDispensationDetailDTO());

		// when
		DispensationDetailDTO response = dispensationDetailController.create(new DispensationDetailDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test(expected = ServiceException.class)
	public void callCreateShouldThrowException(){
		// given
		given(dispensationDetailService.save(null))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		dispensationDetailController.create(null);
	}

	@Test
	public void callFindByIdShouldBeStatusOK() {
		// given
		given(dispensationDetailService.findById(1L))
				.willReturn(MockUtils.mockDispensationDetailDTO());

		// when
		DispensationDetailDTO response = dispensationDetailController.findById(1L);

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callUpdateShouldBeStatusOk() {
		// given
		given(dispensationDetailService.save(MockUtils.mockDispensationDetailDTO()))
				.willReturn(new DispensationDetailDTO());

		// when
		DispensationDetailDTO response  = dispensationDetailController.update(MockUtils.mockDispensationDetailDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callDeleteShouldBeStatusOk() {
		// given
		given(dispensationDetailService.findById(1L))
				.willReturn(MockUtils.mockDispensationDetailDTO());

		// when
		dispensationDetailController.delete(anyLong());

		// then
		verify(dispensationDetailService, times(1)).deleteById(anyLong());
	}

	@Test
	public void callFindAllShouldBeStatusOk() {
		// given
		given(dispensationDetailService.findAllByDispensation(1L,MockUtils.mockPageRequest()))
				.willReturn(MockUtils.mockPageDispensation(MockUtils.mockPageRequest()));

		// when
		Page<DispensationDetailDTO> response = dispensationDetailController.findAll(1L, MockUtils.mockPageRequest());

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindAllShouldThrowException(){
		// given
		given(dispensationDetailService.findAllByDispensation(1L,MockUtils.mockPageRequest()))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		dispensationDetailController.findAll(1L, MockUtils.mockPageRequest());

	}

	@Test
	public void callFindDispensationDetailtBySearchBeStatusOk() {
		// given
		given(dispensationDetailService.findDispensationDetailsBySearch("code", MockUtils.mockPageRequest()))
				.willReturn(MockUtils.mockPageDispensation(MockUtils.mockPageRequest()));

		// when
		Page response = dispensationDetailController
				.findDispensationDetailBySearch(MockUtils.mockDispensationDetailDTO().getCode(), MockUtils.mockPageRequest());

		// then
		assertNotNull(response);
	}

	@Test
	public void callFilterDispensationsShouldBeStatusOk() {
		// given
		given(dispensationDetailService.filterDispensationDetails(MockUtils.mockJsonDispensationDetail(), MockUtils.mockPageRequest()))
				.willReturn(MockUtils.mockPageDispensation(MockUtils.mockPageRequest()));

		// when
		Page response = dispensationDetailController
				.filterDispensations(MockUtils.mockJsonDispensationDetail(), MockUtils.mockPageRequest());

		// then
		assertNotNull(response);
	}

	@Test
	public void callFindMonthlyConsumeShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(dispensationDetailService.findMonthlyConsume(2, false, null, MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapMapStringString());

		// when
		Map<String, Map<String, BigDecimal>> response = dispensationDetailController.findMonthlyConsume(2, token);

		// then
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test
	public void callFindMonthlyConsumeAcumulatedShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(dispensationDetailService.findMonthlyConsumeAcumulated(2, false, null, MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapMapStringString());

		// when
		Map<String, Map<String, BigDecimal>> response = dispensationDetailController.findMonthlyConsumeAcumulated(2, token);

		// then
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test
	public void callFindMonthlyConsumeAvgShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(dispensationDetailService.findMonthlyConsume(2, true, null, MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapMapStringString());

		// when
		Map<String, Map<String, BigDecimal>> response = dispensationDetailController.findMonthlyConsumeAvg(2, token);

		// then
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test
	public void callFindMonthlyConsumeAccumulatedAvgShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(dispensationDetailService.findMonthlyConsumeAcumulated(2, true, null, MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapMapStringString());

		// when
		Map<String, Map<String, BigDecimal>> response = dispensationDetailController.findMonthlyConsumeAcumulatedAvg(2, token);

		// then
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test
	public void callFindTotalCostTreatmentShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(dispensationDetailService.findMonthlyConsume(2, false, "D11AH05", MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapMapStringString());

		// when
		Map<String, Map<String, BigDecimal>> response = dispensationDetailController.findTotalCostTreatment(2, "D11AH05", token);

		// then
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test
	public void callFindTotalCostTreatmentAcumulatedShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(dispensationDetailService.findMonthlyConsumeAcumulated(2, false, "D11AH05", MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapMapStringString());

		// when
		Map<String, Map<String, BigDecimal>> response = dispensationDetailController.findTotalCostTreatmentAcumulated(2, "D11AH05", token);

		// then
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test
	public void callFindTotalCostTreatmentAvgShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(dispensationDetailService.findMonthlyConsume(2, true, "D11AH05", MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapMapStringString());

		// when
		Map<String, Map<String, BigDecimal>> response = dispensationDetailController.findTotalCostTreatmentAvg(2,"D11AH05", token);

		// then
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test
	public void callFindTotalCostTreatmentAccumulatedAvgShouldBeStatusOk() {
		// given
		String token = MockUtils.mockToken();
		given(roleService.getPathologyByRoleSelected(token)).willReturn(MockUtils.mockPathology());
		given(dispensationDetailService.findMonthlyConsumeAcumulated(2, true, "D11AH05", MockUtils.mockPathology()))
				.willReturn(MockUtils.mockMapMapStringString());

		// when
		Map<String, Map<String, BigDecimal>> response = dispensationDetailController.findTotalCostTreatmentAcumulatedAvg(2, "D11AH05", token);

		// then
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

}

