package es.plexus.hopes.hopesback.configuration.security;

import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsMapper {

	public static UserDetails build(User user) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
	}

	private static Set<? extends GrantedAuthority> getAuthorities(User retrievedUser) {
		Set<Role> roles = retrievedUser.getRoles();
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getCode())));
		return authorities;
	}
}
