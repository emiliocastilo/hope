package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.service.DoctorService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DoctorControllerTest {

	@Mock
	private DoctorService doctorService;

	@InjectMocks
	private DoctorController doctorController;

	@Test
	public void getAllDoctorsShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id"));
		given(doctorService.getAllDoctors(any(Pageable.class)))
				.willReturn(getPageableDoctor(pageRequest));

		// when
		ResponseEntity<Page<DoctorDTO>> response = doctorController
				.getAllDoctors(pageRequest);

		// then
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertNotNull(response.getBody());
	}

	@Test
	public void findDoctorsBySearchShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(doctorService.findDoctorsBySearch(any(String.class), any(Pageable.class)))
				.willReturn(getPageableDoctor(pageRequest));

		// when
		Page<DoctorDTO> response = doctorController
				.findDoctorsBySearch(mockDoctorDTO().getName(), pageRequest);

		// then
		assertNotNull(response);
	}

	@Test
	public void filterDoctorsShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(doctorService.filterDoctors(any(String.class), any(Pageable.class)))
				.willReturn(getPageableDoctor(pageRequest));

		// when
		Page<DoctorDTO> response = doctorController
				.filterDoctors(mockJSONDoctor(), pageRequest);

		// then
		assertNotNull(response);
	}

	private String mockJSONDoctor() {
		return "{\"name\":\"" + mockDoctorDTO().getName() + "\"}";
	}

	@Test
	public void getOneDoctorShouldBeStatusOk() {
		// given
		given(doctorService.getOneDoctor(anyLong())).willReturn(mockDoctorDTO());

		// when
		ResponseEntity<DoctorDTO> response = doctorController.getOneDoctor(1L);

		// then
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertNotNull(response.getBody());
	}

	@Test
	public void getOneDoctorShouldBeStatusOkAndEmptyBody() {
		// given
		given(doctorService.getOneDoctor(anyLong())).willReturn(null);

		// when
		ResponseEntity<DoctorDTO> response = doctorController.getOneDoctor(1L);

		// then
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertNull(response.getBody());
	}

	@Test
	public void addDoctorShouldBeStatusCreated() throws ServiceException {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		given(doctorService.addDoctor(any(DoctorDTO.class))).willReturn(mockDoctorDTO());

		// when
		DoctorDTO response = doctorController.addDoctor(mockDoctorDTO());

		// then
		assertNotNull(response);
	}

	@Test
	public void updateDoctorShouldBeStatusOk() throws ServiceException {
		// given
		given(doctorService.getOneDoctor(anyLong())).willReturn(mockDoctorDTO());
		given(doctorService.updateDoctor(any(DoctorDTO.class))).willReturn(mockDoctorDTO());

		// when
		ResponseEntity<DoctorDTO> response = doctorController.updateDoctor(mockDoctorDTO());

		// then
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertNotNull(response.getBody());
	}

	@Test(expected = ServiceException.class)
	public void updateDoctorShouldBeBadRequestWhenNotFound() throws ServiceException {
		// when
		doctorController.updateDoctor(mockDoctorDTO());
	}

	@Test
	public void deleteDoctorShouldBeOk() throws ServiceException {
		// given
		given(doctorService.getOneDoctor(anyLong())).willReturn(mockDoctorDTO());

		// when
		doctorController.deleteDoctor(1L);

		// then
		verify(doctorService, times(1)).deleteDoctor(anyLong());
	}

	private DoctorDTO mockDoctorDTO() {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setId(1L);
		doctorDTO.setName("Paco");
		doctorDTO.setSurname("Gonzales");
		doctorDTO.setPhone("123456789");
		doctorDTO.setDni("12345678Z");
		doctorDTO.setCollegeNumber(123456L);

		return doctorDTO;
	}

	private PageImpl<DoctorDTO> getPageableDoctor(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockDoctorDTO()), pageRequest, 1);
	}
}
