package com.springcore.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.model.Book;

public class App1 {

	public static void main(String[] args) {
// Demo for SIngleton - only one instance of book1 is created and when we call
		//getBean , that instance will be returned
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	 // it returns the single instance
		Book b1 = context.getBean("book1", Book.class);
		System.out.println(b1);
		System.out.println(b1.getBookId() + " " + b1.getBookName() + " " + b1.getPrice());
		System.out.println("--------------b2-----------------");
		 // it returns the single instance - same instance
		Book b2 = context.getBean("book1", Book.class);
		System.out.println(b2);
		b2.setBookName("Java: A Begineer's Guide");
		System.out.println(b2);
		System.out.println("--------------b1-----------------");
		System.out.println(b1);
		System.out.println("b1==b2" + (b1 == b2));
		System.out.println(b1.hashCode());
		System.out.println(b2.hashCode());

	}

}
