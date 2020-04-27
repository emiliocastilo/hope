package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.service.HospitalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
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
		assertNotNull(response);
	}

	private HospitalDTO mockHospitalDTO() {
		HospitalDTO hospitalDTO = new HospitalDTO();
		hospitalDTO.setId(1L);
		hospitalDTO.setName("Test");
		hospitalDTO.setServiceDTO(Collections.emptySet());

		return hospitalDTO;
	}

}
