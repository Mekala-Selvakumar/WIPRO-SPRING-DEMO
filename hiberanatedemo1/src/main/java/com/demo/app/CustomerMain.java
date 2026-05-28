package com.demo.app;

import java.util.Arrays;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.config.HibernateUtils;
import com.demo.model.Customer;
import com.demo.model.Order;

public class CustomerMain {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Scanner sc = new Scanner(System.in);
		Transaction tx = null;

		try (Session session = sessionFactory.openSession();) {
			tx = session.beginTransaction();
			Customer customer1 = new Customer("Tom","tom@gmail.com","9876543210");
			Order  order1 =new Order("Laptop",95000);
			Order order2 = new Order("Mobile",20000);
			order1.setCustomer(customer1);
			order2.setCustomer(customer1);
			customer1.setOrderList(Arrays.asList(order1,order2));
			session.persist(customer1);
			tx.commit();
			System.out.println("Customer Inserted Successfully");
			
		}
		catch(Exception  e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception Occurred  ..." + e.getMessage());
//			e.printStackTrace();
		}
	}

}
