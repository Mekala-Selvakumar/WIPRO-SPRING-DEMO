package com.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;
	@Column(name="customer_name" ,length = 50, nullable = false)
	private String customerName;
	@Column(length = 20)
	private String emailId;
	@Column(name="mobile_number",length = 12)
	private String mobileNumber;
	
	@OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
	private List<Order> orderList;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public Customer( String customerName, String emailId, String mobileNumber ) {
 		this.customerName = customerName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
 	}

	public Customer(int customerId, String customerName, String emailId, String mobileNumber, List<Order> orderList) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.orderList = orderList;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", emailId=" + emailId
				+ ", mobileNumber=" + mobileNumber + ", orderList=" + orderList + "]";
	}
	
	

}
