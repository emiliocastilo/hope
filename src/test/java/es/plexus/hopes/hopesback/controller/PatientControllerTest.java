package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.service.PatientService;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.utils.MockUtils;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientControllerTest {

	@Mock
	private PatientService patientService;

	@InjectMocks
	private PatientController patientController;

	@Test
	public void callFindAllShouldBeStatusOk() {
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("id"));
		// given
		given(patientService.findPatientsByPathology(Collections.singletonList(1L), pageRequest))
				.willReturn(getPageablePatient(pageRequest));

		// when
		Page<PatientDTO> response = patientController.findAll(Collections.singletonList(1L), pageRequest);

		// then		
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isEmpty());
	}

	@Test(expected = ServiceException.class)
	public void callFindAllShouldThrowException() throws Exception {
		final PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id"));
		// given
		given(patientService.findPatientsByPathology(Collections.singletonList(0L), pageRequest))
				.willThrow(new ServiceException("Error: Too much pathologies"));

		// when
		Page<PatientDTO> response = patientController.findAll(Collections.singletonList(0L), pageRequest);

	}

	@Test
	public void callFindByIdShouldBeStatusOK() throws es.plexus.hopes.hopesback.service.exception.ServiceException {
		// given
		given(patientService.findById(1L)).willReturn(MockUtils.mockPatientDTO());

		// when
		PatientDTO response = patientController.findById(1L);

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callFindByIdShouldBeStatusBad() throws es.plexus.hopes.hopesback.service.exception.ServiceException {
		// given
		given(patientService.findById(Mockito.anyLong())).willReturn(null);

		// when
		PatientDTO response = patientController.findById(Mockito.anyLong());

		// then
		Assert.assertNull(response);
	}

	@Test(expected = es.plexus.hopes.hopesback.service.exception.ServiceException.class)
	public void callFindByIdShouldThrowException() throws es.plexus.hopes.hopesback.service.exception.ServiceException {
		// given
		given(patientService.findById(Mockito.anyLong())).willThrow(ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
				"No existe el paciente con el id"));

		// when
		patientController.findById(Mockito.anyLong());
	}

	@Test
	public void callCreateShouldBeStatusOk() throws es.plexus.hopes.hopesback.service.exception.ServiceException {
		// given
		given(patientService.save(new PatientDTO())).willReturn(MockUtils.mockPatientDTO());

		// when
		PatientDTO response = patientController.create(new PatientDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test(expected = ServiceException.class)
	public void callCreateShouldThrowException() throws Exception {
		// given
		given(patientService.save(null)).willThrow(new ServiceException("Error: Too much pathologies"));

		// when
		patientController.create(null);

	}

	@Test
	public void callUpdateShouldBeStatusOk() throws es.plexus.hopes.hopesback.service.exception.ServiceException {

		// given
		given(patientService.findById(anyLong())).willReturn(mockPatientSave());
		given(patientService.save(mockPatientSave())).willReturn(mockPatientSave());

		// when
		PatientDTO response = patientController.update(mockPatientSave());

		// then
		Assert.assertNotNull(response);
	}

	private PatientDTO mockPatientSave() {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setId(1L);
		return patientDTO;
	}

	@Test
	public void callUpdateShouldBeStatusBad() throws es.plexus.hopes.hopesback.service.exception.ServiceException {
		// given
		given(patientService.findById(null)).willReturn(null);

		// when
		PatientDTO response = patientController.update(new PatientDTO());

		// then
		Assert.assertNull(response);
	}

	@Test(expected = ServiceException.class)
	public void callUpdateShouldThrowException() throws Exception {
		// given
		given(patientService.findById(anyLong())).willThrow(new ServiceException("Error: Too much pathologies"));

		// when
		patientController.update(MockUtils.mockPatientDTO());

	}

	//Mocks

	private PageImpl<PatientDTO> getPageablePatient(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(MockUtils.mockPatientDTO()), pageRequest, 1);
	}

	@Test
	public void filterPatientsShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(patientService.filterPatients(any(String.class), any(Pageable.class)))
				.willReturn(getPageablePatient(pageRequest));

		// when
		Page<PatientDTO> response = patientController
				.filterPatients(mockJSONPatient(), pageRequest);

		// then
		assertNotNull(response);
	}

	private String mockJSONPatient() {
		return "{\"name\":\"" + MockUtils.mockPatientDTO().getName() + "\"}";
	}

}

