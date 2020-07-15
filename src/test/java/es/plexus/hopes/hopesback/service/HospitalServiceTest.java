package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.repository.HospitalRepository;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.HospitalMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceTest {

	@Mock
	private HospitalRepository hospitalRepository;

	@Mock
	private HospitalMapper hospitalMapper;

	@InjectMocks
	private HospitalService hospitalService;


	@Test
	public void getAllHospitalsShouldBeStatusOk() {
		// given
		given(hospitalRepository.findAll()).willReturn(Collections.singletonList(mockHospital()));

		// when
		List<HospitalDTO> response = hospitalService.getAllHospitals();

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindByIdShouldBeStatusOk() {
		// given
		given(hospitalRepository.findById(1L)).willReturn(Optional.of(mockHospital()));

		given(hospitalMapper.hospitalToHospitalDTOConverter(mockHospital())).willReturn(mockHospitalDTO());
		// when
		HospitalDTO response = hospitalService.findById(1L);

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
		hospitalService.findById(1L);
	}

	@Test
	public void callGetOneHospitalByIdShouldBeStatusOk() {
		// given
		given(hospitalRepository.findById(1L)).willReturn(Optional.of(mockHospital()));

		// when
		Optional<Hospital> response = hospitalService.getOneHospitalById(1L);

		// then
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.get());
	}

	private Hospital mockHospital() {
		Hospital hospital = new Hospital();
		hospital.setId(1L);
		hospital.setName("Test");
		hospital.setServices(Collections.emptySet());

		return hospital;
	}

	private HospitalDTO mockHospitalDTO() {
		HospitalDTO hospitalDTO = new HospitalDTO();
		hospitalDTO.setId(1L);
		hospitalDTO.setName("Test");
		hospitalDTO.setServiceDTO(Collections.emptySet());

		return hospitalDTO;
	}
}
