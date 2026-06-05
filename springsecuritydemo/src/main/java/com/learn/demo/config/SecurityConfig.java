package com.learn.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfig {

//	 @Bean
//	 public SecurityFilterChain  securityFilterChain(HttpSecurity http)  throws Exception{
//		 return http.authorizeHttpRequests(auth->
//		 auth
////		 1. Restrict the admin endpoints strictly to ADMIN role
//		 .requestMatchers("/admin/**").hasRole("ADMIN")
////		   2. All other endpoints(like  /products) require standard login
//		 .anyRequest().authenticated())
//		 .formLogin(Customizer.withDefaults()) // for browser
//		 .httpBasic(Customizer.withDefaults()) //for POSTMAN
//		 .build();
//	 }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/h2-console/**")
				.permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/product/**").hasAnyRole("USER","ADMIN")
				.anyRequest()
				.authenticated())
				.formLogin(Customizer.withDefaults()) // for browser
				.httpBasic(Customizer.withDefaults());// for POSTMAN

		http.csrf(csrf -> csrf.disable());
		http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
		return http.build();

	}
}
