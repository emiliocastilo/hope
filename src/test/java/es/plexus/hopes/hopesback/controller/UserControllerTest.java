package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllUserShouldBeStatusOk() {
		// given
		given(userService.getAllUsers()).willReturn(Collections.singletonList(mockFullUser()));

		// when
		List<UserDTO> response = userController.getAllUsers();

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
	public void saveUserShouldBeStatusCreated() {
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
	@WithMockUser
	public void chooseProfileShouldBeStatusOk() throws Exception {
		//when
		mockMvc.perform(post("/user/choose_profile")
				.content("ROLE_TEST")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void chooseProfileShouldBeStatusForbidden() throws Exception {
		//when
		mockMvc.perform(post("/user/choose_profile")
				.content("ROLE_TEST")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}


	private UserDTO mockFullUser() {
		final UserDTO user = new UserDTO();
		user.setId(1L);
		user.setUsername("User Name");
		user.setPassword("User password");
		user.setEmail("User email");
		user.setRoles(new HashSet<>(Arrays.asList(1L, 2L)));

		return user;
	}
}
