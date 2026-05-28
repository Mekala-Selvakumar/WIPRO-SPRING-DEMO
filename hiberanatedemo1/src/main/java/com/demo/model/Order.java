package com.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderId;
	@Column(name = "product_name", length = 100, nullable = false)
	private String product;
	private int amount;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Order() {
 	}

	public Order(String product, int amount) {

		this.product = product;
		this.amount = amount;
	}

	public Order(int orderId, String product, int amount, Customer customer) {
		super();
		OrderId = orderId;
		this.product = product;
		this.amount = amount;
		this.customer = customer;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [OrderId=" + OrderId + ", product=" + product + ", amount=" + amount + ", customer=" + customer
				+ "]";
	}

}
