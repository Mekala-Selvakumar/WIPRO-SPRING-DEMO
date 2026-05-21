package com.springcore.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.model.Account;
import com.springcore.model.Book;
import com.springcore.model.Department;
import com.springcore.model.Employee;

public class JavaApp {

	public static void main(String[] args) {
		ApplicationContext  context= new AnnotationConfigApplicationContext(JavaConfig.class);
		
       Department d1=context.getBean("getDepartment",Department.class);
       System.out.println(d1);
       
       Department d2 = context.getBean("department",Department.class);
       System.out.println(d2);
       Book b1 = context.getBean("book1",Book.class);
       System.out.println(b1);
       Employee e1 = context.getBean("employee" ,Employee.class);
       System.out.println(e1);
       Account acc1 = context.getBean("account",Account.class);
       System.out.println(acc1);
	}

}
