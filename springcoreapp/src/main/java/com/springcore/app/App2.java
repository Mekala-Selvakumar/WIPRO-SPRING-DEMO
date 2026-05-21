package com.springcore.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.model.Book;

public class App2 {

	public static void main(String[] args) {
// Demo for prototype
		//each call - getBean - create new instance
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	   //create a new object and returns the object
		Book b1 = context.getBean("book2", Book.class);
		System.out.println(b1);
 		System.out.println("--------------b2-----------------");
 	   //create a new object and returns the object
		Book b2 = context.getBean("book2", Book.class);
		System.out.println(b2);
 		System.out.println("----------After change----b2-----------------");
		b2.setBookName("Java: A Begineer's Guide");
		System.out.println(b2);
		System.out.println("--------------b1-----------------");
		System.out.println(b1);
		System.out.println("b1==b2" + (b1 == b2));
		System.out.println(b1.hashCode());
		System.out.println(b2.hashCode());
	}

}
