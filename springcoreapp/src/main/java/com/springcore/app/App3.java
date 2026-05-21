package com.springcore.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.model.Account;
import com.springcore.model.Book;
import com.springcore.model.Customer;
import com.springcore.model.Employee;

public class App3 {

	public static void main(String[] args) {
 
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
 		Employee  emp1 = context.getBean("employee1", Employee.class);
		System.out.println(emp1);
 		System.out.println("-------------------------------");
 		Employee  emp2 = context.getBean("employee2", Employee.class);
		System.out.println(emp2);
 		System.out.println("-------------------------------");
 		
 		Customer c1 = context.getBean("cust1",Customer.class);
 		System.out.println(c1);
 		Customer c2 = context.getBean("cust2",Customer.class);
 		System.out.println(c2);
 		System.out.println(c1.getCustomerId() + " " +c1.getCustomerName() +"  " +c1.getEmailId());
 		System.out.println("-------------------------------");
 
	
	}

}
