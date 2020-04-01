package es.plexus.hopes.hopesback.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static es.plexus.hopes.hopesback.configuration.security.Constants.HEADER_AUTHORIZACION_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.ISSUER_INFO;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SUPER_SECRET_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_BEARER_PREFIX;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_EXPIRATION_TIME;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
		private AuthenticationManager authenticationManager;
	
		public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
			this.authenticationManager = authenticationManager;
		}
	
		@Override
		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
				throws AuthenticationException {
			try {
				es.plexus.hopes.hopesback.repository.model.User credenciales = new ObjectMapper().readValue(request.getInputStream(), es.plexus.hopes.hopesback.repository.model.User.class);
	
				return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	
		@Override
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
				Authentication auth) {
	
			String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
					.setSubject(((User)auth.getPrincipal()).getUsername())
					.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();

			response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
		}
	}
