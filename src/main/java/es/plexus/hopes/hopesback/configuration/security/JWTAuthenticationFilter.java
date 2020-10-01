package es.plexus.hopes.hopesback.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.LoginDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.service.RoleService;
import es.plexus.hopes.hopesback.service.UserDetailsServiceImpl;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static es.plexus.hopes.hopesback.configuration.security.Constants.FIRST_TOKEN_EXPIRATION_TIME;
import static es.plexus.hopes.hopesback.configuration.security.Constants.HEADER_AUTHORIZACION_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_BEARER_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private UserDetailsServiceImpl userDetailsService;
	private RoleService roleService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
								   UserDetailsService userDetailsService,
								   RoleService roleService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = (UserDetailsServiceImpl) userDetailsService;
		this.roleService = roleService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		Authentication authenticate;

		LoginDTO credentials;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

		return authenticate;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException {

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		List<String> roles = new ArrayList<>();
		if (!authorities.isEmpty()) {
			for (GrantedAuthority authority : authorities) {

				RoleDTO roleDTO = this.roleService.getRoleByCode(authority.getAuthority());

				if (roleDTO != null) {
					StringBuilder role = new StringBuilder();
					role.append(roleDTO.getName()).append(" · ").append(roleDTO.getHospital().getName()).append(" · ").append(roleDTO.getPathology().getName());
					roles.add(role.toString());
				}
			}
		}

		String token = TokenProvider.generateToken(auth, FIRST_TOKEN_EXPIRATION_TIME);

		response.setContentType("application/json");
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("roles", roles);
		out.print(jsonObject);
		out.flush();
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("error", failed.getMessage());
		out.print(jsonObject);
		out.flush();
	}
}
