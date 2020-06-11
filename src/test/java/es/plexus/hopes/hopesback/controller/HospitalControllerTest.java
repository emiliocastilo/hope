package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.service.HospitalService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class HospitalControllerTest {

	@Mock
	private HospitalService hospitalService;

	@InjectMocks
	private HospitalController hospitalController;

	@Test
	public void getAllHospitalsShouldBeStatusOk() {
		// given
		given(hospitalService.getAllHospitals()).willReturn(Collections.singletonList(mockHospitalDTO()));

		// when
		List<HospitalDTO> response = hospitalController.getAllHospitals();

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindByIdShouldBeStatusOk() {
		// given
		given(hospitalService.findById(1L)).willReturn(mockHospitalDTO());

		// when
		HospitalDTO response =hospitalController.findById(1L);

		// then
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getId());
	}

	@Test(expected = ServiceException.class)
	public void callFindByIdThrowException() throws ServiceException  {
		// given
		given(hospitalService.findById(1L))
				.willThrow(ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception());

		// when
		hospitalController.findById(1L);
	}

	private HospitalDTO mockHospitalDTO() {
		HospitalDTO hospitalDTO = new HospitalDTO();
		hospitalDTO.setId(1L);
		hospitalDTO.setName("Test");
		hospitalDTO.setServiceDTO(Collections.emptySet());

		return hospitalDTO;
	}

}
