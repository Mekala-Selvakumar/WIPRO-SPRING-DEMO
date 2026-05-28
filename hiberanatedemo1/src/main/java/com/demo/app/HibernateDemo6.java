package com.demo.app;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.config.HibernateUtils;
import com.demo.dto.BookDTO;
import com.demo.model.Book;

public class HibernateDemo6 {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Scanner sc = new Scanner(System.in);

		try (Session session = sessionFactory.openSession();) {

			System.out.println("Enter Book Id");
			int bid = sc.nextInt();
			String hql = "from Book where bookId=:id";
			Query<Book> query = session.createQuery(hql, Book.class);
			query.setParameter("id", bid);
			Book book1 = query.uniqueResult();
			System.out.println(book1);

			System.out.println("Enter strating book price Range");
			int start = sc.nextInt();
			Query<Book> query1 = session.createQuery("from Book where price>:start", Book.class);
			query1.setParameter("start", start);
			query1.list().forEach(System.out::println);

			List<Book> bookList1 = session
					.createNativeQuery("select * from Book_Master", Book.class).list();
			System.out.println(bookList1);
			System.out.println("----------------");

			String hsql1 ="from Book order by bookName ";
			String hsql2 ="from Book order by price desc";
			session.createQuery(hsql1,Book.class).list().forEach(System.out::println);
			System.out.println("----------------");
			session.createQuery(hsql2,Book.class).list().forEach(System.out::println);
			String  hsql3 ="select bookName,price from Book";
			List<Object[]> list = session.createQuery(hsql3,Object[].class).list();
			for (Object[] obj : list) {
				System.out.println(obj[0] +"  -   " +obj[1]);
			}
			
			List<BookDTO> list1 =session
					.createQuery("select  new com.demo.dto.BookDTO(bookName,price) from Book", BookDTO.class)
					.getResultList();
			list1.forEach(System.out::println);

		} catch (Exception e) {
			System.out.println("Exception Occurred  ..." + e.getMessage());
			e.printStackTrace();
		}

	}

}
