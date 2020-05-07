package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.service.DispensationDetailService;
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

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DispensationDetailControllerTest {

	@Mock
	private DispensationDetailService dispensationDetailService;

	@InjectMocks
	private DispensationDetailController dispensationDetailController;

	@Test
	public void callCreateShouldBeStatusOk() {
		// given
		given(dispensationDetailService.save(new DispensationDetailDTO())).willReturn(mockDispensationDetailDTO());

		// when
		DispensationDetailDTO response = dispensationDetailController.create(new DispensationDetailDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test(expected = ServiceException.class)
	public void callCreateShouldThrowException(){
		// given
		given(dispensationDetailService.save(null)).willThrow(new ServiceException("Error: Object is null"));

		// when
		dispensationDetailController.create(null);

	}

	@Test
	public void callFindByIdShouldBeStatusOK() {
		// given
		given(dispensationDetailService.findById(1L)).willReturn(mockDispensationDetailDTO());

		// when
		DispensationDetailDTO response = dispensationDetailController.findById(1L);

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callUpdateShouldBeStatusOk() {

		// given
		given(dispensationDetailService.save(mockDispensationDetailDTO())).willReturn(new DispensationDetailDTO());

		// when
		DispensationDetailDTO response  = dispensationDetailController.update(mockDispensationDetailDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callDeleteShouldBeStatusOk() {
		// given
		given(dispensationDetailService.findById(anyLong())).willReturn(mockDispensationDetailDTO());

		// when
		dispensationDetailController.delete(anyLong());

		// then
		verify(dispensationDetailService, times(1)).deleteById(anyLong());
	}

	@Test
	public void callFindAllShouldBeStatusOk() {
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("id"));
		// given
		given(dispensationDetailService.findAllByDispensation(anyLong(),any(Pageable.class))).willReturn(mockPageDispensation(pageRequest));

		// when
		Page<DispensationDetailDTO> response = dispensationDetailController.findAll(1L, pageRequest);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindAllShouldThrowException(){
		final PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id"));
		// given
		given(dispensationDetailService.findAllByDispensation(anyLong(),any(Pageable.class)))
				.willThrow(new ServiceException("Error"));

		// when
		dispensationDetailController.findAll(1L, pageRequest);

	}

	@Test
	public void callFindDispensationDetailtBySearchBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(dispensationDetailService.findDispensationDetailsBySearch(any(String.class), any(Pageable.class)))
				.willReturn(mockPageDispensation(pageRequest));

		// when
		Page response = dispensationDetailController
				.findDispensationDetailBySearch(mockDispensationDetailDTO().getCode(), pageRequest);

		// then
		assertNotNull(response);
	}

	@Test
	public void callFilterDispensationsShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(dispensationDetailService.filterDispensationDetails(any(String.class), any(Pageable.class)))
				.willReturn(mockPageDispensation(pageRequest));

		// when
		Page response = dispensationDetailController
				.filterDispensations(mockJsonDispensationDetail(), pageRequest);

		// then
		assertNotNull(response);
	}

	//Mocks
	private DispensationDetailDTO mockDispensationDetailDTO() {
		DispensationDetailDTO dispensationDTO = new DispensationDetailDTO();
		dispensationDTO.setId(1L);
		dispensationDTO.setDispensation(null);
		dispensationDTO.setAmount(new BigDecimal(2));
		dispensationDTO.setQuantity("quantity");
		dispensationDTO.setDescription("description");
		dispensationDTO.setNhc("nhc");
		dispensationDTO.setDaysDispensation(1);
		dispensationDTO.setCode("code");
		dispensationDTO.setNationalCode(1);
		return dispensationDTO;
	}

	private PageImpl<DispensationDetailDTO> mockPageDispensation(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockDispensationDetailDTO()), pageRequest, 1);
	}

	private String mockJsonDispensationDetail() {
		String json = "{\"code\":\"" + mockDispensationDetailDTO().getCode() + "\"}";
		return json;
	}

}

