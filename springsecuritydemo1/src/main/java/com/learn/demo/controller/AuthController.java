package com.learn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.demo.dto.AuthRequest;
import com.learn.demo.util.JwtUtil;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public  String login(@RequestBody AuthRequest  authrequest) {
		System.out.println(authrequest);
		org.springframework.security.core.Authentication auth =
				authManager.authenticate(
						new  UsernamePasswordAuthenticationToken(
								authrequest.getUsername(), 
								authrequest.getPassword()));
		
		if (auth.isAuthenticated()) {
			return jwtUtil.generateToken(authrequest.getUsername());
		}
		return "Invalid Credentials";
	}
}
