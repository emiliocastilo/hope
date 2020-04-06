package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.Role;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	public UsuarioDetailsServiceImpl(UserRepository usuarioRepository) {
		this.userRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		es.plexus.hopes.hopesback.repository.model.User user = userRepository.findByUsername(username).orElse(null);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getUsername(), user.getPassword(), emptyList());
	}

	public List<String> findRolesByUsername(String username) {

		List<String> roles = new ArrayList<>();

		Optional<es.plexus.hopes.hopesback.repository.model.User> byUsername = userRepository.findByUsername(username);

		if (byUsername.isPresent()) {
			for (Role role : byUsername.get().getRoles()) {
				roles.add(role.getName().name());
			}
		}
		else {
			throw new ServiceException("Not found username");
		}

		return roles;
	}
}
