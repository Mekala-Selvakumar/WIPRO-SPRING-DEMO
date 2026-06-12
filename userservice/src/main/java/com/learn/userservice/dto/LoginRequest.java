package com.learn.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	@NotBlank(message = "EmailId is mandatory")
     @Email(message = "emailId shoud be in email format")
	 private String  emailId;
      @NotBlank(message = "Passwod  is mandatory")
	 private String password;
	 

}
