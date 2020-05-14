package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.SectionDTO;
import es.plexus.hopes.hopesback.service.SectionService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SectionControllerTest {

	@Mock
	private SectionService sectionService;

	@InjectMocks
	private SectionController sectionController;

	@Test
	public void callCreateShouldBeStatusOk() {
		// given
		given(sectionService.save(new SectionDTO())).willReturn(mockSectionDTO());

		// when
		SectionDTO response = sectionController.create(new SectionDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callFindByIdShouldBeStatusOK() throws ServiceException {
		// given
		given(sectionService.findById(1L)).willReturn(mockSectionDTO());

		// when
		SectionDTO response = sectionController.findById(1L);

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callUpdateShouldBeStatusOk(){

		// given
		given(sectionService.save(mockSectionDTO())).willReturn(new SectionDTO());

		// when
		SectionDTO response  = sectionController.update(mockSectionDTO());

		// then
		Assert.assertNotNull(response);
	}

	@Test
	public void callDeleteShouldBeStatusOk() throws ServiceException {
		// given
		given(sectionService.findById(anyLong())).willReturn(mockSectionDTO());

		// when
		sectionController.delete(anyLong());

		// then
		verify(sectionService, times(1)).deleteById(anyLong());
	}

	@Test(expected = ServiceException.class)
	public void callDeleteShouldThrowException() throws ServiceException {
		// given
		given(sectionService.deleteById(0L)).willThrow(ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception("No se encuentra el id"));

		// when
		sectionService.deleteById(0L);

	}

	@Test
	public void callFindAllShouldBeStatusOk() throws ServiceException {
		// given
		given(sectionService.findAllSections()).willReturn(mockMenuDto());

		// when
		MenuDTO response = sectionController.findAll();

		// then
		Assert.assertNotNull(response);
	}


	//Mocks
	private SectionDTO mockSectionDTO() {
		SectionDTO sectionDTO = new SectionDTO();
		sectionDTO.setId(2L);
		sectionDTO.setDescription("Description");
		SectionDTO fatherSection = new SectionDTO();
		fatherSection.setId(1L);
		sectionDTO.setFatherSection(fatherSection);
		sectionDTO.setIcon("Icon.png");
		sectionDTO.setActive(true);
		sectionDTO.setOrder(2);
		sectionDTO.setTitle("Title");
		sectionDTO.setUrl("Url");
		return sectionDTO;
	}

	private MenuDTO mockMenuDto() {
		List<MenuDTO> sectionDTOList = new ArrayList<MenuDTO>();
		MenuDTO menu = new MenuDTO();
		menu.setChildren(sectionDTOList);
		return menu;
	}

}

