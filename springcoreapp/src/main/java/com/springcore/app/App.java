package com.springcore.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.model.Book;

public class App {

	public static void main(String[] args) {
 
		ApplicationContext context =  new ClassPathXmlApplicationContext("beans.xml");
		Book b1 = context.getBean("book1", Book.class);
		System.out.println(b1);
		System.out.println(b1.getBookId() +" " +b1.getBookName()+" " +b1.getPrice());
		System.out.println("-------------------------------");
		Book b2 = context.getBean("book2", Book.class);
		System.out.println(b2);
		System.out.println(b2.getBookId() +" " +b2.getBookName()+" " +b2.getPrice());
		
		     
		     
	}

}
