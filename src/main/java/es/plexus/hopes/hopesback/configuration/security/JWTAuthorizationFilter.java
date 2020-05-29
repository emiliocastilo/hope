package es.plexus.hopes.hopesback.configuration.security;

import es.plexus.hopes.hopesback.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

		UserDetails user = userDetailsService.loadUserByUsername(userName);
		return TokenProvider.getAuthentication(tokenHopes, user);
	}

	private boolean isRequestPostPhoto(final HttpServletRequest httpServletRequest) {
		return HttpMethod.POST.toString().equalsIgnoreCase(httpServletRequest.getMethod()) &&
						  httpServletRequest.getRequestURI().endsWith(Constants.ADD_PHOTO_BY_QR_URL);
	}
}
