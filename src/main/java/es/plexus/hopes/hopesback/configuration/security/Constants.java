package es.plexus.hopes.hopesback.configuration.security;

public class Constants {

	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	public static final String ISSUER_INFO = "hopes";
	public static final String SECRET_KEY = "HoPes1234";
	public static final long FIRST_TOKEN_EXPIRATION_TIME = 300; // 5min
	public static final long SECOND_TOKEN_EXPIRATION_TIME = 28800; // 8h
	public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";

}
