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

public class HibernateDemo5 {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Scanner sc = new Scanner(System.in);

		String hql = "from Book  where price >=1500";
		try (Session session = sessionFactory.openSession();) {
			List<Book> bookList = session.createQuery(hql, Book.class).list();
			bookList.forEach(System.out::println);
			session.createQuery("from Book where bookId=5", Book.class).list().forEach(System.out::println);
			Book book1 = session.createQuery("from Book where bookId=5", Book.class).uniqueResult();
			System.out.println(book1);
			// More than one row or book -
			// org.hibernate.NonUniqueResultException: Query did not return a unique result:
			// 2 results were returned
//			Book book2 = session.createQuery("from Book where price>1300", Book.class)
//			.uniqueResult();
//			System.out.println(book2);

		} catch (Exception e) {
			System.out.println("Exception Occurred  ..." + e.getMessage());
			e.printStackTrace();
		}

	}

}
