package com.learn.userservice.controller;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.userservice.dto.LoginRequest;
import com.learn.userservice.dto.RegistrationResponse;
import com.learn.userservice.entity.User;
import com.learn.userservice.exceptions.EmailIdAlreadyExistsException;
import com.learn.userservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/users")
	public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody User user)
			throws EmailIdAlreadyExistsException {

		RegistrationResponse response = userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PostMapping("/users/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest  loginRequest){
		ResponseEntity<?> entity=null;
		RegistrationResponse response = userService.login(loginRequest);
		if (response==null) {
			entity = new ResponseEntity<String>("Invalid UserName/Password", HttpStatus.BAD_REQUEST);
	        
		}
		else {
//			entity =new ResponseEntity<RegistrationResponse>(response, HttpStatus.OK);
		   String token =getToken(loginRequest.getEmailId());
			entity = new ResponseEntity<String>(token, HttpStatus.OK);
		}
		return entity;
	}
	
	
	private String  getToken(String emailId) {
		Date today= new Date();
//		Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.

		long time1 =today.getTime();
		long exipryTime = time1+(1000*60*60);
		Key key =Keys.hmacShaKeyFor("Secret-Key-32-byte-WIPRO-JAVA--FSD-Success!".getBytes(StandardCharsets.UTF_8));
		return  Jwts.builder().issuedAt(new Date())
				.expiration(new Date(exipryTime))
				 .subject(emailId)
				 .signWith(key)
				 .compact();

	}
}
