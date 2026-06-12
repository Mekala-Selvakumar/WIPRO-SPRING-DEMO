package com.learn.userservice.service;

import com.learn.userservice.dto.LoginRequest;
import com.learn.userservice.dto.RegistrationResponse;
import com.learn.userservice.entity.User;
import com.learn.userservice.exceptions.EmailIdAlreadyExistsException;

public interface UserService {
	
	public RegistrationResponse registerUser(User  user) throws EmailIdAlreadyExistsException;
    public  RegistrationResponse  login(LoginRequest  loginRequest);
}
