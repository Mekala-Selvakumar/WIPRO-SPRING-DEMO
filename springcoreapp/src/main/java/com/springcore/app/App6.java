package com.springcore.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.model.Account;
import com.springcore.model.Book;
import com.springcore.model.Customer;
import com.springcore.model.Employee;

public class App6{

	public static void main(String[] args) {
 
		ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
   		 Employee emp1 = context.getBean("employee1",Employee.class);
   		 System.out.println(emp1);
   		 Employee emp2 = context.getBean("employee2",Employee.class);
   		 System.out.println(emp2);
   		 Account  acc1 = context.getBean("account1",Account.class);
   		 System.out.println(acc1);

	}

}
