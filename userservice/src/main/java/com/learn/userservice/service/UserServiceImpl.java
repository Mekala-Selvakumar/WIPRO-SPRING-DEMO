package com.learn.userservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learn.userservice.dto.LoginRequest;
import com.learn.userservice.dto.RegistrationResponse;
import com.learn.userservice.entity.User;
import com.learn.userservice.exceptions.EmailIdAlreadyExistsException;
import com.learn.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public RegistrationResponse registerUser(User user) throws EmailIdAlreadyExistsException {
 		Optional<User> op = userRepository.findById(user.getEmailId());
 		if (op.isPresent()) {
			throw new EmailIdAlreadyExistsException(user.getEmailId() + " is already Exist ");

		}
		User createdUser = userRepository.save(user);
		RegistrationResponse response = new RegistrationResponse(createdUser.getEmailId(), createdUser.getUsername(),
				createdUser.getMobileNumber(), createdUser.getGender());

		return response;

	}

	@Override
	public RegistrationResponse login(LoginRequest loginRequest) {
		RegistrationResponse response = null;
		Optional<User> op = userRepository.findById(loginRequest.getEmailId());
		if (op.isPresent()) {
			User existingUser = op.get();
			if (loginRequest.getPassword().equals(existingUser.getPassword())) {
				response = new RegistrationResponse(existingUser.getUsername(), existingUser.getEmailId(),
						existingUser.getMobileNumber(), existingUser.getGender());
			}
		}
		return response;

	}

}
