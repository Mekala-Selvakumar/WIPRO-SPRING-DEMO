package com.springcore.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.model.Account;
import com.springcore.model.Book;
import com.springcore.model.Customer;
import com.springcore.model.Department;
import com.springcore.model.Employee;

public class AnnotApp {

	public static void main(String[] args) {
		ApplicationContext  context= 
				new AnnotationConfigApplicationContext(AnnotConfig.class);
         Book  b1 = context.getBean("book1",Book.class);
         System.out.println(b1);
         
         Department d1 = context.getBean("department",Department.class);
         System.out.println(d1);
         
         Employee  e1 = context.getBean("employee",Employee.class);
         System.out.println(e1);
         
         Customer c1 = context.getBean("customer", Customer.class);
         System.out.println(c1);
         
         Account a1 = context.getBean("account",Account.class);
         System.out.println(a1);
		
		
	}

}
