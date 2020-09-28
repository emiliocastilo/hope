package es.plexus.hopes.hopesback.configuration.security;

import es.plexus.hopes.hopesback.configuration.LDAPConfiguration;
import es.plexus.hopes.hopesback.service.RoleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static es.plexus.hopes.hopesback.configuration.security.Constants.LOGIN_URL;
import static es.plexus.hopes.hopesback.configuration.security.Constants.REQUEST_PASS_CHANGE_URL;
import static es.plexus.hopes.hopesback.configuration.security.Constants.RESET_PASS_URL;
import static es.plexus.hopes.hopesback.configuration.security.Constants.SAVE_NEW_PASS_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final RoleService roleService;
	private final LDAPConfiguration ldapConfiguration;

	public WebSecurity(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, RoleService roleService,
					   LDAPConfiguration ldapConfiguration) {
		this.userDetailsService = userDetailsService;
		this.roleService = roleService;
		this.ldapConfiguration = ldapConfiguration;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.cors().and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/actuator/**", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
				.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
				.antMatchers(HttpMethod.POST, REQUEST_PASS_CHANGE_URL).permitAll()
				.antMatchers(HttpMethod.GET, RESET_PASS_URL).permitAll()
				.antMatchers(HttpMethod.POST, SAVE_NEW_PASS_URL).permitAll()
				.anyRequest().authenticated().and()
				.addFilterBefore(new JWTAuthenticationFilter(authenticationManager(), userDetailsService, roleService), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthorizationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
				.and()
			.ldapAuthentication()
				.userDnPatterns(ldapConfiguration.getDnPatterns())
				.groupSearchBase(ldapConfiguration.getGroupSearchBase())
				.contextSource()
					.url(ldapConfiguration.getUrl())
					.and()
				.passwordCompare()
					.passwordEncoder(new BCryptPasswordEncoder())
					.passwordAttribute(ldapConfiguration.getPasswordAttribute());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
		corsConfiguration.addExposedHeader("Authorization");

		source.registerCorsConfiguration("/**", corsConfiguration);

		return source;
	}


}
