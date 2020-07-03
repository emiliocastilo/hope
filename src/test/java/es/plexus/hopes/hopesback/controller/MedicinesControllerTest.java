package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MedicineDTO;
import es.plexus.hopes.hopesback.service.MedicineService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
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
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MedicinesControllerTest {

	@Mock
	private MedicineService medicineService;

	@InjectMocks
	private MedicineController medicineController;

	@Test
	public void callCreateShouldBeStatusOk() throws ServiceException {
		// given
		given(medicineService.save(new MedicineDTO())).willReturn(mockMedicineDTO());

		// when
		MedicineDTO response = medicineController.create(new MedicineDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test(expected = ServiceException.class)
	public void callCreateShouldThrowException() throws ServiceException {
		// given
		given(medicineService.save(null)).willThrow(new ServiceException("CODE_ERROR", HttpStatus.BAD_REQUEST, "ERROR_DEV"));

		// when
		medicineController.create(null);

	}

	@Test
	public void callFindByIdShouldBeStatusOK() {
		// given
		given(medicineService.findById(1L)).willReturn(mockMedicineDTO());

		// when
		MedicineDTO response = medicineController.findById(1L);

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callUpdateShouldBeStatusOk() throws ServiceException {

		// given
		given(medicineService.save(mockMedicineDTO())).willReturn(new MedicineDTO());

		// when
		MedicineDTO response  = medicineController.update(mockMedicineDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callDeleteShouldBeStatusOk() {
		// given
		given(medicineService.findById(anyLong())).willReturn(mockMedicineDTO());

		// when
		medicineController.delete(anyLong());

		// then
		verify(medicineService, times(1)).deleteById(anyLong());
	}

	@Test
	public void callFindAllShouldBeStatusOk() {
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("id"));
		// given
		given(medicineService.findAllMedicines(any(Pageable.class))).willReturn(mockPageMedicine(pageRequest));

		// when
		Page<MedicineDTO> response = medicineController.findAll(pageRequest);

		// then
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isEmpty());
	}

	@Test
	public void callFindDispensationDetailtBySearchBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(medicineService.findMedicinesBySearch(any(String.class), any(Pageable.class)))
				.willReturn(mockPageMedicine(pageRequest));

		// when
		Page<MedicineDTO> response = medicineController.findBySearch(mockMedicineDTO().getAcronym(), pageRequest);

		// then
		assertNotNull(response);
	}

	@Test
	public void callFilterDispensationsShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(medicineService.filterMedicines(any(String.class), any(Pageable.class)))
				.willReturn(mockPageMedicine(pageRequest));

		// when
		Page<MedicineDTO> response = medicineController.filters(mockJsonMedicine(), pageRequest);

		// then
		assertNotNull(response);
	}

	//Mocks
	private MedicineDTO mockMedicineDTO() {
		MedicineDTO medicineDTO = new MedicineDTO();
		medicineDTO.setId(1L);
		medicineDTO.setAcronym("A");
		medicineDTO.setActIngredients("ActIngredients");
		medicineDTO.setCodeAct("Code Act");
		medicineDTO.setDescription("Description");
		medicineDTO.setNationalCode("NationalCode");
		medicineDTO.setPresentation("Presentation");
		medicineDTO.setCommercialization(true);
		return medicineDTO;
	}

	private PageImpl<MedicineDTO> mockPageMedicine(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockMedicineDTO()), pageRequest, 1);
	}

	private String mockJsonMedicine() {
		String json = "{\"acronym\":\"" + mockMedicineDTO().getAcronym() + "\"}";
		return json;
	}

}

