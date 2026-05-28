package com.demo.app;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import com.demo.config.HibernateUtils;
import com.demo.dto.BookDTO;
import com.demo.model.Book;

public class HibernateDemo7 {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Scanner sc = new Scanner(System.in);
		Transaction tx=null;

		try (Session session = sessionFactory.openSession();) {
			long sum = session.createQuery("select sum(price) from Book", Long.class).uniqueResult();
			System.out.println(sum);
//			tx = session.beginTransaction();
      
			//select - createQuery
			// update/delete/insert  - MutationQuery
//			MutationQuery query  = 
//					session.createMutationQuery("update Book set price=:price where bookId=:id");
//			query.setParameter("price",2000);
//			query.setParameter("id", 5);
//			int rows = query.executeUpdate();
//			System.out.println(rows + "rows updated");
//			tx.commit();
			
			List<Book> bookList1 = 
					session.createNamedQuery("Book.findAll",Book.class).getResultList();
			bookList1.forEach(System.out::println);
			
			Query<Book> query1 = session.createNamedQuery("Book.findByPrice", Book.class);
			query1.setParameter("price", 1200);
			query1.list().forEach(System.out::println);
			
 		} catch (Exception e) {
			if (tx!=null) {
				tx.rollback();
			}
			System.out.println("Exception Occurred  ..." + e.getMessage());
			e.printStackTrace();
		}

	}

}
