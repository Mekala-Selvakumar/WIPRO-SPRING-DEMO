package com.springcore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.springcore.model.Account;
import com.springcore.model.Book;
import com.springcore.model.Customer;
import com.springcore.model.Department;
import com.springcore.model.Employee;

@Configuration
public class JavaConfig {
	// here bean name is getDepartment
	@Bean
	public Department getDepartment() {
		Department d1 = new Department();
		d1.setDepartmentId(10);
		d1.setDepartmentName("Admin");
		d1.setLocation("Chennai");
		return d1;
	}

	// bean name : department
	@Bean
	public Department department() {
		Department d1 = new Department();
		d1.setDepartmentId(20);
		d1.setDepartmentName("Finance");
		d1.setLocation("Chennai");
		return d1;
	}

	//  bean name is book
	@Bean
	public Book book() {
		Book b1 = new Book();
		b1.setBookId(1);
		b1.setBookName("Java");
		b1.setPrice(2000);
		return b1;
	}

	// beanName : book1
	@Bean(name = "book1")
	public Book getBook() {
		Book b1 = new Book();
		b1.setBookId(2);
		b1.setBookName("Head First- Spring");
		b1.setPrice(2000);
		return b1;
	}
	
	@Bean
	public Employee    employee() {
		Employee e1 = new Employee();
		e1.setEmployeeId(1234);
		e1.setEmployeeName("Tom");
		e1.setSalary(20000);
//		e1.setDepartment(getDepartment());
		e1.setDepartment(department());
		return e1;
	}
	
	@Bean
//	@Bean(initMethod = "init" , destroyMethod = "destroy")
//	@Scope("prototype")
	public Customer  customer() {
		return new Customer(9876,"Murugan","murugan@gmail.com");
	}
	
	@Bean
	public Account  account() {
		 return new  Account(567,"Savings",20000, customer());
	}

}
