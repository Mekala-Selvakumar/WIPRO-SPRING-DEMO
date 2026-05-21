package com.springcore.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Customer {
	private int customerId;
	private String customerName;
	private String emailId;
	
//	public Customer() {
//		System.out.println("This is default constructor");
// 	}
	public Customer( @Value("1") int customerId,
			@Value("tom") String customerName, 
			@Value("tom@gmail.com")  String emailId) {
 		this.customerId = customerId;
		this.customerName = customerName;
		this.emailId = emailId;
		
		System.out.println("Prameterized Constructor  : "  +customerId);
	}
	public int getCustomerId() {
		return customerId;
	}
//	public void setCustomerId(int customerId) {
//		this.customerId = customerId;
//	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return String.format("Customer [customerId=%s, customerName=%s, emailId=%s]", customerId, customerName,
				emailId);
	}
	
	public void init() {
		System.out.println("It is init method - init method called after instantiated");
		this.customerId=8001;
		this.customerName="Arun Kumar";
		this.emailId="arun@gmail.com";
	}
	public void destroy() {
		System.out.println("It is destroy method");
	}
	

}
