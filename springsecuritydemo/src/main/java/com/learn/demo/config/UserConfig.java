package com.learn.demo.config;

import java.lang.module.ModuleDescriptor.Builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
public class UserConfig {

	 
//	@Bean
	public UserDetailsService  userDetailsService(PasswordEncoder  encoder) {
		UserDetails user = User.builder()
				.username("tom")
				.password(encoder.encode("tom123"))
				.roles("USER")
				.build();
		
		UserDetails user1 = User.builder()
				.username("admin")
				.password(encoder.encode("admin123"))
				.roles("ADMIN")
				.build();
				
				return new InMemoryUserDetailsManager(user,user1);
						
	}
}
