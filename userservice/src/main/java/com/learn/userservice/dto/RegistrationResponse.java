package com.learn.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//UserDTO
public class RegistrationResponse {
	
	private String username;
	private String emailId;
	private String  mobileNumber;
	private String gender;
	
	

}
