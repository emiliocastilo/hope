package es.plexus.hopes.hopesback.configuration.security;

import es.plexus.hopes.hopesback.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static es.plexus.hopes.hopesback.configuration.security.Constants.AUTHORITIES_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SECRET_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.TOKEN_HOPES_KEY;


public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private UserDetailsServiceImpl userDetailsService;

	public JWTAuthorizationFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = (UserDetailsServiceImpl) userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
									FilterChain filterChain) throws ServletException, IOException {

		String authorizationHeader = httpServletRequest.getHeader(Constants.HEADER_AUTHORIZACION_KEY);

		if (StringUtils.isEmpty(authorizationHeader) || !authorizationHeader
				.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		final String token = authorizationHeader.replace(Constants.TOKEN_BEARER_PREFIX, "");
		UsernamePasswordAuthenticationToken authenticationToken = fillAuthenticationToken(httpServletRequest, token);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private UsernamePasswordAuthenticationToken fillAuthenticationToken(final HttpServletRequest httpServletRequest, final String token) {
		String tokenHopes = token;
		String userName;


		try {
			userName = TokenProvider.getUserName(tokenHopes);
		} catch (Exception e){
			if(isRequestPostPhoto(httpServletRequest)){
				Jws<Claims> qrClaims = TokenProvider.getClaimsByQrTokenAndKey(token);
				tokenHopes = qrClaims.getBody().get(TOKEN_HOPES_KEY, String.class)
						.replace(Constants.TOKEN_BEARER_PREFIX, "");
			}
			userName = TokenProvider.getUserName(tokenHopes);
		}

		try {
			UserDetails user = userDetailsService.loadUserByUsername(userName);
			return TokenProvider.getAuthentication(tokenHopes, user);
		} catch (UsernameNotFoundException ex) {
			// cuando el usuario es de LDAP
			JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
			Jws<Claims> claims = jwtParser.parseClaimsJws(token);
			Collection<SimpleGrantedAuthority> authorities =
					Arrays.stream(claims.getBody().get(AUTHORITIES_KEY).toString().split(","))
							.map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList());
			return new UsernamePasswordAuthenticationToken(claims.getBody().getSubject(), claims.getSignature(), authorities);
		}
	}

	private boolean isRequestPostPhoto(final HttpServletRequest httpServletRequest) {
		return HttpMethod.POST.toString().equalsIgnoreCase(httpServletRequest.getMethod()) &&
						  httpServletRequest.getRequestURI().endsWith(Constants.ADD_PHOTO_BY_QR_URL);
	}
}
