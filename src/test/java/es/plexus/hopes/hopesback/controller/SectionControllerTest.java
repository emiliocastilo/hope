package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.PathologyDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.controller.model.SectionDTO;
import es.plexus.hopes.hopesback.controller.model.ServiceDTO;
import es.plexus.hopes.hopesback.service.SectionService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

	@Test
	public void callFindAllShouldBeStatusOk() throws ServiceException {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setCode("ROLE_CODE");
		roleDTO.setDescription("Descripción rol");
		HospitalDTO hospitalDTO = new HospitalDTO();
		roleDTO.setHospital(hospitalDTO);
		ServiceDTO serviceDTO = new ServiceDTO();
		roleDTO.setService(serviceDTO);
		PathologyDTO pathologyDTO = new PathologyDTO();
		roleDTO.setPathology(pathologyDTO);
		roleDTO.setName("Nombre rol");

		// given
		given(sectionService.findAllSectionsByRole(any())).willReturn(mockMenuDto());

		// when
		MenuDTO response = sectionController.findAllByRole(roleDTO);

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

