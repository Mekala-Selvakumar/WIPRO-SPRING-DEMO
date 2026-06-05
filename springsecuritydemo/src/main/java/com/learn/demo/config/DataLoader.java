package com.learn.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 import org.springframework.security.crypto.password.PasswordEncoder;

import com.learn.demo.entity.User;
import com.learn.demo.repository.UserRepository;

@Configuration
public class DataLoader {

 
 
 @Bean
 CommandLineRunner loadUsers(UserRepository repo, PasswordEncoder encoder) {
	 return args->{
		 repo.save( new User("tom",encoder.encode("tom123"),"USER"));
		 repo.save(new User("admin", encoder.encode("admin123"), "ADMIN"));
	 };
	 
 }

}
