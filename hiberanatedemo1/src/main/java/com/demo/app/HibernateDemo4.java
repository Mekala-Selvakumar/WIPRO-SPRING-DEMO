package com.demo.app;

import java.util.List;
import java.util.Scanner;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.config.HibernateUtils;
import com.demo.model.Book;

public class HibernateDemo4 {

	public static void main(String[] args) {
		
		SessionFactory  sessionFactory = HibernateUtils.getSessionFactory();
 		Transaction  tx=null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Book Id");
		int id =sc.nextInt();
 		
		try(Session session = sessionFactory.openSession();) {
//			Book  book = session.get(Book.class, id);  //eager loading    - Hibernate method
			//returns null if not found , no exception
			//immediately  fetch the object from the database (hits database immediately)
			Book book = session.find(Book.class, id); //eager loading - JPA method
			if (book!=null) {
				System.out.println(book);
			}
			else {
				System.out.println("Book with id : " +id + " not found");
			}
 			 
			//Lazy loading
			// No SQL  immediately ( it won't hit the database immediately) 
			//whenever you access the object (book2), that time only it hit the database
			//proxy object returned
			Book book2 = session.getReference(Book.class,id);  // object created , no db query yet
			//SQL executes when property accessed
			//if object not found , it throws ObjectNotFoundException (EntityNotFoundException)
			System.out.println(book2.getBookName()); // Sql executes here
			System.out.println(book2);
  
		}
		catch(ObjectNotFoundException  e ) {
			System.out.println("Book with (lazy loading) : " +id +" Not Found");
		}
		catch(Exception e) {
 			System.out.println("Exception Occurred  ..." +e.getMessage() );
		e.printStackTrace();
		}
		finally {
			HibernateUtils.shutdown();
			sc.close();
		}
		 
	}

}
