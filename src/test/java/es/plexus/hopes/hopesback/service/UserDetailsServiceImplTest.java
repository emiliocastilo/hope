package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.User;
import org.hibernate.service.spi.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {

	@Configuration
	static class ContextConfiguration {
		@Bean
		public UserDetailsServiceImpl UserDetailsService(final UserRepository userRepository) {
			return Mockito.spy(new UserDetailsServiceImpl(userRepository));
		}
	}

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void callLoadUserByUsernameShouldWorkSuccessfully() {
		// given
		given(userRepository.findByUsername(anyString())).willReturn(Optional.of(mockFullUser()));

		// when
		UserDetails user = userDetailsService.loadUserByUsername("test");

		// then
		assertNotNull(user);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void callLoadUserByUsernameShouldFailWhenUserNameIsNull() {
		userDetailsService.loadUserByUsername(null);
	}

	@Test
	public void callFindRolesByUsernameShouldWorkSuccessfully() {
		// given
		given(userRepository.findByUsername(anyString())).willReturn(Optional.of(mockFullUser()));

		// when
		List<String> roles = userDetailsService.findRolesByUsername("test");

		// then
		assertNotNull(roles);
	}

	@Test(expected = ServiceException.class)
	public void callFindRolesByUsernameShouldFailWhenHasNoRoles() {
		// given
		given(userRepository.findByUsername(anyString())).willReturn(Optional.empty());

		// when
		userDetailsService.findRolesByUsername("test");
	}

	private User mockFullUser() {
		final User user = new User();
		user.setId(1L);
		user.setUsername("User Name");
		user.setPassword("User password");
		user.setEmail("User email");
		user.setRoles(new HashSet<>(Collections.singletonList(mockFullRole())));

		return user;
	}

	private Role mockFullRole() {
		final Role role = new Role();
		role.setId(1L);
		role.setName("ROLE_ADMIN");
		role.setCode("ROLE_ADMIN");
		role.setDescription("Rol Description");

		return role;
	}
}
