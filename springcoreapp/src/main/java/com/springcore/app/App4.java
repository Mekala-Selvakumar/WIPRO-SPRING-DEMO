package com.springcore.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.model.Account;
import com.springcore.model.Book;
import com.springcore.model.Customer;
import com.springcore.model.Employee;

public class App4 {

	public static void main(String[] args) {
 
		ApplicationContext context = new ClassPathXmlApplicationContext("beans1.xml");
 		Employee  emp1 = context.getBean("employee1", Employee.class);
		System.out.println(emp1);
 		System.out.println("-------------------------------");
  		Customer c1 = context.getBean("",Customer.class);
 		System.out.println(c1);
 		System.out.println(c1.getCustomerId() + " " +c1.getCustomerName() +"  " +c1.getEmailId());
 		System.out.println("-------------------------------");
         Account  acc1 = context.getBean("account1",Account.class);
         System.out.println(acc1);
  		System.out.println("--------------cust1-----------------");

//   		Customer c2 = context.getBean("cust2",Customer.class);
//  		System.out.println(c2);
  		
  		Customer c3 = context.getBean("cust3",Customer.class);
  		System.out.println(c3);
  		// shutdown the container
  		((ClassPathXmlApplicationContext)context).close();

	}

}
