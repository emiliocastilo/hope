package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.configuration.security.UserDetailsMapper;
import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.Role;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		final es.plexus.hopes.hopesback.repository.model.User retrievedUser = userRepository.findByUsername(userName).orElse(null);
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return UserDetailsMapper.build(retrievedUser);
	}

	public List<String> findRolesByUsername(String username) {

		List<String> roles = new ArrayList<>();

		Optional<es.plexus.hopes.hopesback.repository.model.User> byUsername = userRepository.findByUsername(username);

		if (byUsername.isPresent()) {
			for (Role role : byUsername.get().getRoles()) {
				roles.add(role.getName().name());
			}
		} else {
			throw new ServiceException("Not found username");
		}

		return roles;
	}
}
