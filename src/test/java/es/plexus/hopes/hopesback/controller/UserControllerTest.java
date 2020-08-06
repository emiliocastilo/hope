package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PasswordDTO;
import es.plexus.hopes.hopesback.controller.model.RequestPasswordChangeDTO;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
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

	private UserDTO mockFullUser() {
		final UserDTO user = new UserDTO();
		user.setId(1L);
		user.setUsername("User Name");
		user.setEmail("User email");
		user.setRoles(new HashSet<>(Arrays.asList(1L, 2L)));

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
}
