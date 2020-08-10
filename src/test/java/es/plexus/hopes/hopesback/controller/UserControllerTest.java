package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.PathologyDTO;
import es.plexus.hopes.hopesback.controller.model.RequestPasswordChangeDTO;
import es.plexus.hopes.hopesback.controller.model.ServiceDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserUpdateDTO;
import es.plexus.hopes.hopesback.repository.model.Token;
import es.plexus.hopes.hopesback.service.UserService;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Test
	public void getAllUserShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id"));
		given(userService.getAllUsers(any(Pageable.class))).willReturn(new PageImpl<>(Collections.singletonList(mockFullUser()), pageRequest, 1));

		// when
		Page<UserDTO> response = userController.getAllUsers(pageRequest);

		// then
		assertNotNull(response);
	}

	@Test
	public void getOneUserByIdShouldBeStatusOk() {
		// given
		given(userService.getOneUserById(anyLong())).willReturn(mockFullUser());

		// when
		UserDTO response = userController.getOneUserById(1L);

		// then
		assertNotNull(response);
	}

	@Test
	public void saveUserShouldBeStatusCreated() throws ServiceException {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		given(userService.addUser(any(UserDTO.class))).willReturn(mockFullUser());

		// when
		UserDTO response = userController.saveUser(mockFullUser());

		// then
		assertNotNull(response);
	}

	@Test
	public void findUsersBySearchShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(userService.findUsersBySearch(any(String.class), any(Pageable.class)))
				.willReturn(getPageableUser(pageRequest));

		// when
		Page<UserDTO> response = userController
				.findUsersBySearch(mockFullUser().getName(), pageRequest);

		// then
		assertNotNull(response);
	}

	@Test
	public void filterUsersShouldBeStatusOk() {
		// given
		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name"));
		given(userService.filterUsers(any(String.class), any(Pageable.class)))
				.willReturn(getPageableUser(pageRequest));

		// when
		Page<UserDTO> response = userController.filterUsers(mockJSONUser(), pageRequest);

		// then
		assertNotNull(response);
	}

	@Test
	public void updateUserShouldBeStatusOk() throws ServiceException {
		// given
		given(userService.getOneUserById(anyLong())).willReturn(mockFullUser());
		given(userService.updateUser(any(UserUpdateDTO.class))).willReturn(mockFullUser());

		// when
		UserDTO response = userController.updateUser(mockUserUpdateDTO());

		// then
		assertNotNull(response);
	}

	@Test(expected = ServiceException.class)
	public void updateUserShouldBeBadRequestWhenNotFound() throws ServiceException {
		// when
		userController.updateUser(mockUserUpdateDTO());
	}

	@Test
	public void deleteDoctorShouldBeOk() throws ServiceException {
		// given
		given(userService.getOneUserById(anyLong())).willReturn(mockFullUser());

		// when
		userController.deleteUser(1L);

		// then
		verify(userService, times(1)).deleteUser(anyLong());
	}

	private UserDTO mockFullUser() {
		final UserDTO user = new UserDTO();
		user.setId(1L);
		user.setUsername("User Username");
		user.setEmail("User email");
		user.setRoles(new HashSet<>(Arrays.asList(1L, 2L)));
		user.setHospitalId(1L);
		user.setCollegeNumber(1L);
		user.setDni("User DNI");
		user.setName("User Name");
		user.setSurname("User Surname");
		user.setPhone("User Phone");

		final ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setId(1L);
		serviceDTO.setName("Service Name");

		final PathologyDTO pathologyDTO = new PathologyDTO();
		pathologyDTO.setDescription("Pathology Description");
		pathologyDTO.setId(1L);
		pathologyDTO.setName("Pathology Name");

		serviceDTO.setPathologies(new HashSet<>(Collections.singletonList(pathologyDTO)));

		user.setService(serviceDTO);

		return user;
	}

	private PasswordDTO mockPassword() {
		final PasswordDTO password = new PasswordDTO();
		password.setToken("a7631aea-c582-4e4c-99a6-0d1d48955f98");
		password.setPassword("password");
		password.setNewPassword("12345");
		return password;
	}

	private RequestPasswordChangeDTO mockRequestPassword() {
		final RequestPasswordChangeDTO request = new RequestPasswordChangeDTO();
		request.setEmail("User email");

		return request;
	}

	@Test
	public void requestPasswordChangeBeStatusOk() throws Exception {
		// when
		userController.requestPasswordChange(mockRequestPassword());

		verify(userService, times(1)).requestPasswordChange(anyString());
	}

	@Test
	public void resetPasswordBeStatusOk() {
		// given
		given(userService.resetPassword(anyString())).willReturn(new Token());

		// when
		String response = userController.resetPassword(mockPassword().getToken());

		// then
		assertNotNull(response);
	}

	@Test
	public void saveNewPasswordShouldBeStatusOk() throws ServiceException {
		// given
		given(userController.saveNewPassword(any(PasswordDTO.class))).willReturn(new String());

		// when
		String response = userController.saveNewPassword(mockPassword());

		// then
		assertNotNull(response);
	}

	@Test
	public void updatePasswordShouldBeStatusOk() throws ServiceException {
		// given
		given(userController.updatePassword(any(PasswordDTO.class))).willReturn(new String());

		// when
		String response = userController.updatePassword(mockPassword());

		assertNotNull(response);
	}

	private PageImpl<UserDTO> getPageableUser(PageRequest pageRequest) {
		return new PageImpl<>(Collections.singletonList(mockFullUser()), pageRequest, 1);
	}

	private String mockJSONUser() {
		return "{\"name\":\"" + mockFullUser().getName() + "\"}";
	}

	private UserUpdateDTO mockUserUpdateDTO() {
		UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
		userUpdateDTO.setCollegeNumber(2L);
		userUpdateDTO.setDni("User DNI update");
		userUpdateDTO.setEmail("User Email update");
		userUpdateDTO.setId(1L);
		userUpdateDTO.setName("User Name update");
		userUpdateDTO.setPhone("User Phone update");

		return userUpdateDTO;
	}
}
