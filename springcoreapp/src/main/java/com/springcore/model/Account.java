package com.springcore.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Account {
	private long accountNumber;
	private String accountType;
	private int balance;
	private Customer  customer;
	
	@Autowired
	public Account(@Value("1001") long accountNumber,
			@Value("Savings")  String accountType,
			@Value("10000") int balance, Customer customer) {
 		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.customer = customer;
		System.out.println("It is Account - Parameterized Constructor");
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return String.format("Account [accountNumber=%s, accountType=%s, balance=%s, customer=%s]", accountNumber,
				accountType, balance, customer);
	}
	
	

}
