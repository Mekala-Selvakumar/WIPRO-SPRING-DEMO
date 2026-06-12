package com.learn.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@Getter
//@ToString
//@Setter
//@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User_Master")
public class User {

	@Id
	@Column(name = "EMAIL_ID", length = 30)
	@NotBlank(message="emailId is mandatory")
	@Email(message="emailId should be in email format")
	private String emailId;
	
	@Column(name = "USER_NAME", length = 30, nullable = false)
	@NotBlank(message = "Username is mandatory")
	private String username;
	
	@Column(length = 20, nullable = false)
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@Column(name = "MOBILE_NUMBER", length = 15)
	private String mobileNumber;
	
	@Column(length = 15)
	private String gender;

}
