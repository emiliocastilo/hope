package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DispensationDTO;
import es.plexus.hopes.hopesback.controller.model.FormDispensationDTO;
import es.plexus.hopes.hopesback.service.DispensationService;
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

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DispensationControllerTest {

	@Mock
	private DispensationService dispensationService;

	@InjectMocks
	private DispensationController dispensationController;

	@Test
	public void callCreateShouldBeStatusOk() {
		// given
		given(dispensationService.save(new FormDispensationDTO())).willReturn(mockDispensationDTO());

		// when
		DispensationDTO response = dispensationController.create(new FormDispensationDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test(expected = ServiceException.class)
	public void callCreateShouldThrowException(){
		// given
		given(dispensationService.save(null)).willThrow(new ServiceException("Error: Object is null"));

		// when
		dispensationController.create(null);

	}

	@Test
	public void callFindByIdShouldBeStatusOK() {
		// given
		given(dispensationService.findById(1L)).willReturn(mockDispensationDTO());

		// when
		DispensationDTO response = dispensationController.findById(1L);

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callDeleteShouldBeStatusOk() {
		// given
		given(dispensationService.findById(anyLong())).willReturn(mockDispensationDTO());

		// when
		dispensationController.delete(anyLong());

		// then
		verify(dispensationService, times(1)).deleteById(anyLong());
	}

	@Test
	public void callFindAllShouldBeStatusOk() {
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("id"));
		// given
		given(dispensationService.findAll(any(Pageable.class))).willReturn(mockPageDispensation(pageRequest));

		// when
		Page<DispensationDTO> response = dispensationController.findAll(pageRequest);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindAllShouldThrowException(){
		final PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id"));
		// given
		given(dispensationService.findAll(any(Pageable.class)))
				.willThrow(new ServiceException("Error"));

		// when
		dispensationController.findAll(pageRequest);

	}

	//Mocks
	private DispensationDTO mockDispensationDTO() {
		DispensationDTO dispensationDTO = new DispensationDTO();
		dispensationDTO.setId(1L);
		dispensationDTO.setDate(LocalDateTime.now());
		dispensationDTO.setStartPeriod(LocalDateTime.now());
		dispensationDTO.setEndPeriod(LocalDateTime.now());
		dispensationDTO.setNumRecords(23);
		return dispensationDTO;
	}
	
	private PageImpl<DispensationDTO> mockPageDispensation(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockDispensationDTO()), pageRequest, 1);
	}


}

