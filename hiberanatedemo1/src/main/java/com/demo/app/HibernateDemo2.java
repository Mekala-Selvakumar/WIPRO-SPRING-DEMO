package com.demo.app;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.config.HibernateUtils;
import com.demo.model.Book;

public class HibernateDemo2 {

	public static void main(String[] args) {
		
		SessionFactory  sessionFactory = HibernateUtils.getSessionFactory();
		Session  session=null;
		Transaction  tx=null;
		Scanner sc = new Scanner(System.in);
		
		try {
			session =sessionFactory.openSession();
			System.out.println("Enter Book Name :");
			String bookName=sc.nextLine();
			System.out.println("Enter Book Price :");
			int price = sc.nextInt();
			tx =session.beginTransaction();
			Book book =new Book(bookName, price);
			session.persist(book);
			System.out.println("Book Saved with id : " +book.getBookId());
			tx.commit();
			System.out.println("Book Inserted Successfully.....");
		}
		catch(Exception e) {
			if (tx!=null) {
				tx.rollback();
			}
			System.out.println("Exception Occurred while inserting ..." +e.getMessage() );
//			e.printStackTrace();
		}
		finally {
			 if (session!=null) {
				 session.close();
			 }
		}
 
	}

}
