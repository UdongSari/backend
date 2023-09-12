package com.udongsari.configure;

import com.udongsari.account.repository.AccountRepository;
import com.udongsari.configure.jwt.JwtAuthenticationFilter;
import com.udongsari.configure.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CorsConfig corsConfig;
	private final AccountRepository accountRepository;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http
			.formLogin().disable()
			.httpBasic().disable()
			.apply(new customFilter());

		http
			.authorizeRequests(authorizeRequests ->
					authorizeRequests
							.antMatchers("/api/v1/user/**")
							.access("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")

							.antMatchers("/api/v1/manager/**")
							.access("hasRole('MANAGER') or hasRole('ADMIN')")

							.antMatchers("/api/v1/admin/**")
							.access("hasRole('ADMIN')")

							.anyRequest().permitAll()
			);

		return http.build();
	}

	public class customFilter extends AbstractHttpConfigurer<customFilter, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

			CorsFilter corsFilter = corsConfig.corsFilter();

			JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager);
			jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

			JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager, accountRepository);

			http
					.addFilter(corsFilter)
					.addFilter(jwtAuthenticationFilter)
					.addFilter(jwtAuthorizationFilter)
			;
		}
	}

}
