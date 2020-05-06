package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.service.RoleService;
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

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

	@Mock
	private RoleService roleService;

	@InjectMocks
	private RoleController roleController;

	@Test
	public void getAllRolesShouldBeStatusOk() {
		// given
		given(roleService.getAllRoles()).willReturn(Collections.singletonList(mockRoleDTO()));

		// when
		List<RoleDTO> response = roleController.getAllRoles();

		// then
		assertNotNull(response);
	}

	@Test
	public void getOneRoleShouldBeStatusOk() {
		// given
		given(roleService.getOneRoleById(anyLong())).willReturn(mockRoleDTO());

		// when
		RoleDTO response = roleController.getOneRole(1L);

		// then
		assertNotNull(response);
	}

	@Test
	public void filterRoleShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(roleService.findRolesBySearch(anyString(), any(Pageable.class)))
				.willReturn(getPageableRole(pageRequest));

		// when
		Page<RoleDTO> response = roleController.filterRole("name", pageRequest);

		// then
		assertNotNull(response);
	}

	@Test
	public void addRoleShouldBeStatusCreated() {
		// given
		given(roleService.addRole(any(RoleDTO.class))).willReturn(mockRoleDTO());

		// when
		RoleDTO response = roleController.addRole(mockRoleDTO());

		// then
		assertNotNull(response);
	}

	@Test
	public void updateShouldBeStatusOk() throws ServiceException {
		// given
		given(roleService.updateRole(any(RoleDTO.class))).willReturn(mockRoleDTO());

		// when
		RoleDTO response = roleController.updateRole(mockRoleDTO());

		// then
		assertNotNull(response);
	}

	@Test(expected = org.hibernate.service.spi.ServiceException.class)
	public void updateShouldBeServiceException() throws ServiceException {
		// given
		given(roleService.updateRole(any(RoleDTO.class)))
				.willThrow(new org.hibernate.service.spi.ServiceException("Error"));

		// when
		roleController.updateRole(mockRoleDTO());
	}

	@Test
	public void deleteRoleShouldBeStatusOk() throws ServiceException {
		// when
		roleController.deleteRole(1L);

		// then
		verify(roleService, times(1)).deleteRole(anyLong());
	}

	@Test
	public void getMenuByRoleNameShouldBeStatusOk() throws ServiceException {
		// given
		given(roleService.getMenuByRole(anyLong())).willReturn(mockMenuDTO());

		// when
		MenuDTO response = roleController.getMenuByRoleName(1L);

		// then
		assertNotNull(response);
	}

	@Test(expected = org.hibernate.service.spi.ServiceException.class)
	public void getMenuByRoleNameShouldBeServiceException() throws ServiceException {
		// given
		given(roleService.getMenuByRole(anyLong()))
				.willThrow(new org.hibernate.service.spi.ServiceException("Error"));

		// when
		roleController.getMenuByRoleName(1L);
	}


	private RoleDTO mockRoleDTO() {
		final RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(1L);
		roleDTO.setName("Test");

		return roleDTO;
	}

	private MenuDTO mockMenuDTO() {
		final MenuDTO menuDTO = new MenuDTO();
		menuDTO.setId(1L);
		return menuDTO;
	}

	private PageImpl<RoleDTO> getPageableRole(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockRoleDTO()), pageRequest, 1);
	}

}
