package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PathologyDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.service.PatientService;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientControllerTest {

	@Mock
	private PatientService patientService;

	@InjectMocks
	private PatientController patientController;

	@Test
	public void callFindAllShouldBeStatusOk() {
		// given
		given(patientService.findPatientsByPathology(1L)).willReturn(mockPatientList());

		// when
		ResponseEntity<List<PatientDTO>> response = patientController.findAll(1L);

		// then
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
		Assert.assertTrue(!response.getBody().isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindAllShouldThrowException() throws Exception {
		// given
		given(patientService.findPatientsByPathology(0L)).willThrow(new ServiceException("Error: Too much pathologies"));

		// when
		ResponseEntity<List<PatientDTO>> response = patientController.findAll(0L);

	}

	@Test
	public void callFindByIdShouldBeStatusOK() {
		// given
		given(patientService.findById(1L)).willReturn(Optional.of(mockPatientDTO()));

		// when
		ResponseEntity<PatientDTO> response = patientController.findById(1L);

		// then
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void callFindByIdShouldBeStatusBad() {
		// given
		given(patientService.findById(Mockito.anyLong())).willReturn(Optional.empty());

		// when
		ResponseEntity<PatientDTO> response = patientController.findById(Mockito.anyLong());

		// then
		Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		Assert.assertNull(response.getBody());
	}

	@Test(expected = NullPointerException.class)
	public void callFindByIdShouldThrowException() throws Exception {
		// given
		given(patientService.findById(Mockito.anyLong())).willReturn(null);

		// when
		ResponseEntity<PatientDTO> response = patientController.findById(Mockito.anyLong());
	}

	@Test
	public void callCreateShouldBeStatusOk() {
		// given
		given(patientService.save(new PatientDTO())).willReturn(mockPatientDTO());

		// when
		ResponseEntity<List<PatientDTO>> response = patientController.create(new PatientDTO());

		// then
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
	}

	@Test(expected = ServiceException.class)
	public void callCreateShouldThrowException() throws Exception {
		// given
		given(patientService.save(null)).willThrow(new ServiceException("Error: Too much pathologies"));

		// when
		ResponseEntity<PatientDTO> response = patientController.create(null);

	}

	@Test
	public void callUpdateShouldBeStatusOk() {

		// given
		given(patientService.findById(1L)).willReturn(Optional.of(mockPatientDTO()));
		given(patientService.save(mockPatientDTO())).willReturn(mockPatientDTO());

		// when
		ResponseEntity<PatientDTO> response = patientController.update(mockPatientDTO());

		// then
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void callUpdateShouldBeStatusBad() {
		// given
		given(patientService.findById(null)).willReturn(Optional.empty());

		// when
		ResponseEntity<PatientDTO> response = patientController.update(new PatientDTO());

		// then
		Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		Assert.assertNull(response.getBody());
	}

	@Test(expected = ServiceException.class)
	public void callUpdateShouldThrowException() throws Exception {
		// given
		given(patientService.findById(1L)).willReturn(Optional.of(new PatientDTO()));

		// given
		given(patientService.save(mockPatientDTO())).willThrow(new ServiceException("Error: Too much pathologies"));

		// when
		ResponseEntity<PatientDTO> response = patientController.update(mockPatientDTO());

	}

	//Mocks
	private List<PatientDTO> mockPatientList() {
		List<PatientDTO> patientDtoList = new ArrayList<PatientDTO>();

		PatientDTO patientDto = mockPatientDTO();
		patientDtoList.add(patientDto);

		return  patientDtoList;
	}

	private PatientDTO mockPatientDTO() {
		PatientDTO patientDto = new PatientDTO();

		patientDto.setAddress("Calle Falsa, 1");
		patientDto.setBirthDate(LocalDate.now());
		patientDto.setDni("012345678W");
		patientDto.setEmail("email@hopes.com");
		patientDto.setId(1L);
		patientDto.setName("Peter");
		patientDto.setFirstSurname("Perez");
		patientDto.setLastSurname("Perez");
		patientDto.setGenderCode("M");
		patientDto.setNhc("NHC0001");
		patientDto.setHealthCard("HC0001");
		patientDto.setPhone("+356663");

		Hospital hospital = new Hospital();
		hospital.setId(1L);
		patientDto.setHospital(hospital);


		Set<PathologyDTO> pathologies = new HashSet<>();
		PathologyDTO pathology =  new PathologyDTO();
		pathologies.add(pathology);
		patientDto.setPathologies(pathologies);
		return patientDto;
	}

}
