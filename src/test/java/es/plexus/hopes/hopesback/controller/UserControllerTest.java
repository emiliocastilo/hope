package es.plexus.hopes.hopesback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.HopesBackApplication;
import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.configuration.security.WebSecurity;
import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.ERole;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = {UserController.class, WebSecurity.class, HopesBackApplication.class})
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	@Spy
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;

	@MockBean
	private UserRepository userRepository;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	public void callSaveUserShouldWorkCorrectlyWithStatus200() throws Exception {
		// given
		User user = mockFullUser();

		final String request = objectMapper.writeValueAsString(user);
		final String token = mockTokenAccess();

		// when
		mockMvc.perform(post("/user/")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token)
				.content(request))
				.andExpect(status().isOk());

		// then
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	public void callSaveUserShouldWorkFailWhenNoToken() throws Exception {
		// given
		User user = mockFullUser();

		final String request = objectMapper.writeValueAsString(user);

		// when
		mockMvc.perform(post("/user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().isForbidden());
	}

	@Test
	public void callGetAllUsersShouldWorkCorrectlyWithStatus200() throws Exception {
		// given
		final String token = mockTokenAccess();

		// when
		String response = mockMvc.perform(get("/user/")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		final ArrayList responseList = objectMapper.readValue(response, ArrayList.class);

		// then
		assertNotNull(responseList);
	}

	@Test
	public void callGetAllUsersShouldWorkFailWhenNoToken() throws Exception {
		// given
		final String token = mockTokenAccess();

		// when
		mockMvc.perform(get("/user/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void callGetUserShouldWorkCorrectlyWithStatus200() throws Exception {
		// given
		final String token = mockTokenAccess();

		given(userRepository.findByUsername("test")).willReturn(Optional.of(mockFullUser()));

		// when
		String response = mockMvc.perform(get("/user/{username}", "test")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		final User user = objectMapper.readValue(response, User.class);

		// then
		assertNotNull(user);
	}

	@Test
	public void callGetUserShouldWorkFailWhenNoToken() throws Exception {
		// given
		given(userRepository.findByUsername("test")).willReturn(Optional.of(mockFullUser()));

		// when
		mockMvc.perform(get("/user/{username}", "test")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void callChooseProfileShouldWorkCorrectlyWithStatus200() throws Exception {
		// given
		final String token = mockTokenAccess();

		// when
		ResultActions result = mockMvc.perform(post("/user/choose_profile")
				.header("Authorization", "Bearer " + token)
				.content("ROLE_ADMIN")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		MockHttpServletResponse response = result.andReturn().getResponse();
		String newToken = response.getHeader("Authorization");

		// then
		assertNotNull(newToken);
		assertNotEquals(token, newToken);
	}

	@Test
	public void callChooseProfileShouldWorkFailWhenNoToken() throws Exception {
		// when
		mockMvc.perform(post("/user/choose_profile")
				.content("ROLE_ADMIN")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	//Mocks
	private String mockTokenAccess() {
		final String token = TokenProvider.generateToken("admin", ERole.ROLE_ADMIN, 5000);
		final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		final org.springframework.security.core.userdetails.User userSecurity =
				new org.springframework.security.core.userdetails
						.User("admin", "password", authorities);

		given(userRepository.findByUsername(anyString())).willReturn(Optional.of(mockFullUser()));
		given(userDetailsService.loadUserByUsername(anyString())).willReturn(userSecurity);
		return token;
	}

	private User mockFullUser() {
		final User user = new User();
		user.setId(1L);
		user.setUsername("User Name");
		user.setPassword("User password");
		user.setEmail("User email");
		user.setDateCreation(LocalDate.now());
		user.setDateModification(LocalDate.now());
		user.setHospital(mockFullHospital());
		user.setRoles(new HashSet<>(Collections.singletonList(mockFullRole())));

		return user;
	}

	private Hospital mockFullHospital() {
		final Hospital hospital = new Hospital();
		hospital.setId(1L);
		hospital.setName("Hospital Name");

		return hospital;
	}

	private Role mockFullRole() {
		final Role role = new Role();
		role.setId(1);
		role.setName(ERole.ROLE_ADMIN);
		role.setDescription("Rol Description");

		return role;
	}
}
