package com.demo.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.config.HibernateUtils;
import com.demo.model.Book;

public class HibernateDemo1 {

	public static void main(String[] args) {
		
		SessionFactory  sessionFactory = HibernateUtils.getSessionFactory();
		Session  session=null;
		Transaction  tx=null;
		
		try {
			session =sessionFactory.openSession();
			tx =session.beginTransaction();
			Book book =new Book("Hibernate",850);
			session.persist(book);
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
