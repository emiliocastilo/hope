package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.service.RoleService;
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

	private RoleDTO mockRoleDTO() {
		final RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(1L);
		roleDTO.setName("Test");

		return roleDTO;
	}

}
