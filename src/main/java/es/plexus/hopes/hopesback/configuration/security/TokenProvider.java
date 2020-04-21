package es.plexus.hopes.hopesback.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static es.plexus.hopes.hopesback.configuration.security.Constants.AUTHORITIES_KEY;
import static es.plexus.hopes.hopesback.configuration.security.Constants.ISSUER_INFO;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SECRET_KEY;

public class TokenProvider {

	private TokenProvider() {
	}

	public static String generateToken(Authentication authentication, long expirationTime) {

		final String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer(ISSUER_INFO)
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
				.compact();
	}

	public static String generateToken(String username, String role, long expirationTime) {

		return Jwts.builder()
				.setSubject(username)
				.claim(AUTHORITIES_KEY, role)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer(ISSUER_INFO)
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
				.compact();
	}

	public static UsernamePasswordAuthenticationToken getAuthentication(final String token,
																		final UserDetails userDetails) {

		final JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<SimpleGrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	public static String getUserName(final String token) {
		final JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		return claimsJws.getBody().getSubject();
	}
}
