package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.service.DoctorService;
import org.hibernate.service.spi.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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
		given(doctorService.getAllDoctors()).willReturn(Collections.singletonList(mockDoctorDTO()));

		// when
		ResponseEntity<List<DoctorDTO>> response = doctorController.getAllDoctors();

		// then
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertNotNull(response.getBody());
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
	public void addDoctorShouldBeStatusCreated() {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		given(doctorService.addDoctor(any(DoctorDTO.class))).willReturn(mockDoctorDTO());

		// when
		ResponseEntity<DoctorDTO> response = doctorController.addDoctor(mockDoctorDTO());

		// then
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(response.getBody());
	}

	@Test
	public void updateDoctorShouldBeStatusOk() {
		// given
		given(doctorService.updateDoctor(any(DoctorDTO.class))).willReturn(mockDoctorDTO());

		// when
		ResponseEntity<DoctorDTO> response = doctorController.updateDoctor(1L, mockDoctorDTO());

		// then
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertNotNull(response.getBody());
	}

	@Test(expected = ServiceException.class)
	public void updateDoctorShouldBeServiceExceptionWhenNotFound() {
		// given
		given(doctorService.updateDoctor(any(DoctorDTO.class))).willThrow(ServiceException.class);

		// when
		doctorController.updateDoctor(1L, mockDoctorDTO());
	}

	@Test
	public void deleteDoctorShouldBeOk() {
		// when
		doctorController.deleteDoctor(1L);

		// then
		verify(doctorService, times(1)).deleteDoctor(anyLong());
	}

	private DoctorDTO mockDoctorDTO() {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setName("Paco");
		doctorDTO.setSurname("Gonzales");
		doctorDTO.setPhone("123456789");
		doctorDTO.setDni("12345678Z");
		doctorDTO.setCollegeNumber(123456L);
		doctorDTO.setActive(true);
		doctorDTO.setDateCreate(LocalDate.now());
		doctorDTO.setDateModify(LocalDate.now());
		doctorDTO.setUser(null);
		doctorDTO.setServiceId(1L);

		return doctorDTO;
	}
}
