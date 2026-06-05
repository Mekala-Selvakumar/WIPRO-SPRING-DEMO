package com.learn.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learn.demo.filter.JwtFilter;

@Configuration
public class SecurityConfig {

//	@Autowired
	private JwtFilter jwtFiter;
	
	public SecurityConfig(JwtFilter  jwtFilter) {
		this.jwtFiter=jwtFilter;
	}
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> 
				auth.requestMatchers("/login","/register").permitAll()
				.requestMatchers("/admin/**")
						.hasRole("ADMIN").anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFiter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}
	
	@Bean
	public AuthenticationManager  authenticationManager(
			AuthenticationConfiguration   config) throws Exception {
		return config.getAuthenticationManager();
	}

}
