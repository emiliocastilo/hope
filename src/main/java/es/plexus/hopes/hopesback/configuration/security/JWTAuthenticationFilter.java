package es.plexus.hopes.hopesback.configuration.security;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.LoginDTO;
import es.plexus.hopes.hopesback.service.UsuarioDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static es.plexus.hopes.hopesback.configuration.security.Constants.HEADER_AUTHORIZACION_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.ISSUER_INFO;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SUPER_SECRET_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_BEARER_PREFIX;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_EXPIRATION_TIME;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private UsuarioDetailsServiceImpl userDetailsService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = (UsuarioDetailsServiceImpl) userDetailsService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		Authentication authenticate;

		LoginDTO credentials = getCredentials(request);

		if (credentials.getRole() == null) {
			authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("", ""));
		}
		else {
			authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
		}

		return authenticate;
	}

	private LoginDTO getCredentials(HttpServletRequest request)  {

		LoginDTO loginDTO;

		try {
			loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

			List<String> roles = userDetailsService.findRolesByUsername(loginDTO.getUsername());

			if (loginDTO.getRole() == null && (roles.isEmpty() || roles.size() > 1)) {
				loginDTO.setRole(null);
			}
			else if (loginDTO.getRole() == null && roles.size() == 1) {
				loginDTO.setRole(roles.get(0));
			}
			else {

				boolean correctRole = false;

				for (String role : roles) {

					if (role.equals(loginDTO.getRole())) {
						correctRole = true;
						loginDTO.setRole(role);
						break;
					}
				}

				if (!correctRole) {
					loginDTO.setRole(null);
				}
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		return loginDTO;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {

		String token = Jwts.builder()
				.setIssuedAt(new Date())
				.setIssuer(ISSUER_INFO)
				.setSubject(((User)auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY)
				.compact();
				//TODO anyadir el rol al token

		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); //  SC_CONTINUE
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("error", "Choose one role");
		jsonObject.put("roles", "[ROLE_MANAGER, ROLE_DOCTOR]");
		out.print(jsonObject);
		out.flush();
	}
}
