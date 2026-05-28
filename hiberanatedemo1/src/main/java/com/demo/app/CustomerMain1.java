package com.demo.app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.config.HibernateUtils;
import com.demo.model.Customer;
import com.demo.model.Order;

public class CustomerMain1 {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		try (Session session = sessionFactory.openSession();) {
			Customer customer = session.find(Customer.class, 3);
			System.out.println("Customer Details");
			System.out.println("Customer  Id : " + customer.getCustomerId());
			System.out.println("Customer Name : " + customer.getCustomerName());
			System.out.println("Email Id : " + customer.getEmailId());
			System.out.println("Mobile Number :" + customer.getMobileNumber());
			List<Order> orderList = customer.getOrderList();
			for (Order order : orderList) {
				System.out.printf("%-10s%-20s%-10s\n", order.getOrderId(), order.getProduct(), order.getAmount());
			}
		} catch (Exception e) {
			System.out.println("Exception Occurred  ..." + e.getMessage());
//			e.printStackTrace();
		}
	}

}
