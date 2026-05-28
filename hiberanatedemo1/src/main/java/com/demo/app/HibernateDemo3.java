package com.demo.app;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.config.HibernateUtils;
import com.demo.model.Book;

public class HibernateDemo3 {

	public static void main(String[] args) {
		
		SessionFactory  sessionFactory = HibernateUtils.getSessionFactory();
		Session  session=null;
		Transaction  tx=null;
 		
		try {
			session =sessionFactory.openSession();
			 Query<Book> query=  session.createQuery("from Book",Book.class); 
			 List<Book> bookList =query.getResultList();
			 bookList.forEach(System.out::println);
			 
			 long count =query.getResultCount();
			 System.out.println("Total number of rows = " +count);
  
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
