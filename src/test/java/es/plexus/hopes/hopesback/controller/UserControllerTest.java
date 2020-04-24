//package es.plexus.hopes.hopesback.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
//import es.plexus.hopes.hopesback.controller.model.RoleDTO;
//import es.plexus.hopes.hopesback.controller.model.UserDTO;
//import es.plexus.hopes.hopesback.repository.model.User;
//import es.plexus.hopes.hopesback.service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
//
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserControllerTest {
//
//	@LocalServerPort
//	int randomServerPort;
//
//	private static final String URL_TEMPLATE = "/user";
//
//	// Componente de Spring para pruebas IT
//	private MockMvc mockMvc;
//
//	// Dependencias del controlador
//	@MockBean
//	private UserService userService;
//
//	// Contexto web de la aplicacion
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//				.apply(springSecurity())
//				.build();
//	}
//
//	@Test
//	public void callSaveUserShouldWorkCorrectlyWithStatus200() throws Exception {
//		// given
//		UserDTO user = mockFullUser();
//		final String requestUser = objectMapper.writeValueAsString(user);
//		final String token = mockTokenAccess();
//
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		// when
//		mockMvc.perform(post(URL_TEMPLATE)
//				.contentType(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + token)
//				.content(requestUser))
//				.andExpect(status().isCreated());
//
//		// then
//		verify(userService, times(1)).addUser(any(UserDTO.class));
//	}
//
//	@Test
//	public void callSaveUserShouldWorkFailWhenNoToken() throws Exception {
//		// given
//		UserDTO user = mockFullUser();
//
//		final String request = objectMapper.writeValueAsString(user);
//
//		// when
//		mockMvc.perform(post(URL_TEMPLATE)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(request))
//				.andExpect(status().isForbidden());
//	}
//
//	@Test
//	public void callGetAllUsersShouldWorkCorrectlyWithStatus200() throws Exception {
//		// given
//		final String token = mockTokenAccess();
//
//		// when
//		String response = mockMvc.perform(get(URL_TEMPLATE)
//				.header("Authorization", "Bearer " + token)
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andReturn().getResponse().getContentAsString();
//
//		final ArrayList responseList = objectMapper.readValue(response, ArrayList.class);
//
//		// then
//		assertNotNull(responseList);
//	}
//
//	@Test
//	public void callGetAllUsersShouldWorkFailWhenNoToken() throws Exception {
//		// given
//		final String token = mockTokenAccess();
//
//		// when
//		mockMvc.perform(get(URL_TEMPLATE)
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isForbidden());
//	}
//
//	@Test
//	public void callGetUserShouldWorkCorrectlyWithStatus200() throws Exception {
//		// given
//		final String token = mockTokenAccess();
//
//		given(userService.getOneUserByName("test")).willReturn(mockFullUser());
//
//		// when
//		String response = mockMvc.perform(get(URL_TEMPLATE + "/{username}", "test")
//				.header("Authorization", "Bearer " + token)
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated())
//				.andReturn().getResponse().getContentAsString();
//
//		final User user = objectMapper.readValue(response, User.class);
//
//		// then
//		assertNotNull(user);
//	}
//
//	@Test
//	public void callGetUserShouldWorkFailWhenNoToken() throws Exception {
//		// given
//		given(userService.getOneUserByName(anyString())).willReturn(mockFullUser());
//
//		// when
//		mockMvc.perform(get(URL_TEMPLATE + "{username}", "test")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isForbidden());
//	}
//
//	@Test
//	public void callChooseProfileShouldWorkCorrectlyWithStatus200() throws Exception {
//		// given
//		final String token = mockTokenAccess();
//
//		// when
//		ResultActions result = mockMvc.perform(post(URL_TEMPLATE + "/choose_profile")
//				.header("Authorization", "Bearer " + token)
//				.content("ROLE_ADMIN")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//
//		MockHttpServletResponse response = result.andReturn().getResponse();
//		String newToken = response.getHeader("Authorization");
//
//		// then
//		assertNotNull(newToken);
//		assertNotEquals(token, newToken);
//	}
//
//	@Test
//	public void callChooseProfileShouldWorkFailWhenNoToken() throws Exception {
//		// when
//		mockMvc.perform(post(URL_TEMPLATE + "/choose_profile")
//				.content("ROLE_ADMIN")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isForbidden());
//	}
//
//	//Mocks
//	private String mockTokenAccess() {
//		given(userService.getOneUserByName(anyString())).willReturn(mockFullUser());
//
//		return TokenProvider.generateToken("admin", "ROLE_ADMIN", 5000);
//	}
//
//	private UserDTO mockFullUser() {
//		final UserDTO user = new UserDTO();
//		user.setId(1L);
//		user.setUsername("User Name");
//		user.setPassword("User password");
//		user.setEmail("User email");
//		user.setRoleDTOS(new HashSet<>(Collections.singletonList(mockFullRole())));
//
//		return user;
//	}
//
//	private RoleDTO mockFullRole() {
//		final RoleDTO role = new RoleDTO();
//		role.setId(1L);
//		role.setName("ROLE_ADMIN");
//
//		return role;
//	}
//}
