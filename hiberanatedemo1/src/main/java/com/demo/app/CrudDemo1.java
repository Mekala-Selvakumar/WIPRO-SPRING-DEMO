package com.demo.app;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.config.HibernateUtils;
import com.demo.model.Book;

public class CrudDemo1 {

	public static void main(String[] args) {
		int choice = 1;
		Scanner sc = new Scanner(System.in);
		while (choice != 6) {
			printMenu();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				sc.nextLine();
				System.out.println("Enter Book Name :");
				String bookName = sc.nextLine();
				System.out.println("Enter Book Price :");
				int price = sc.nextInt();
				Book book = new Book(bookName, price);
				addBook(book);
				break;
			case 2:
				getAllBooks();
				break;
			case 3:
				System.out.println("Enter Book Id :");
				int id = sc.nextInt();
				getBookById(id);
				break;
			case 4:
				System.out.println("Enter Book Id :");
				int bid = sc.nextInt();
				deleteBook(bid);
				break;
			case 5:
				System.out.println("Enter Book Id :");
				 bid = sc.nextInt();
				 updateBook(bid);
				break;
			case 6:
				System.out.println("Exiting ...");
				break;
			default:
				System.out.println("Invalid Choice ...");
			}
		}

	}

	public static void printMenu() {
		System.out.println("---- Menu-----");
		System.out.println("1. Add Book");
		System.out.println("2. Display Books");
		System.out.println("3. Search Book");
		System.out.println("4. Delete Book");
		System.out.println("5. Update Book");
		System.out.println("6. Exit");
	}

	public static void addBook(Book book) {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.persist(book);
			System.out.println("Book Saved with id : " + book.getBookId());
			tx.commit();
			System.out.println("Book Inserted Successfully.....");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception Occurred while inserting ..." + e.getMessage());
//			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void getAllBooks() {

		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			Query<Book> query = session.createQuery("from Book", Book.class);
			List<Book> bookList = query.getResultList();
			bookList.forEach(System.out::println);

			long count = query.getResultCount();
			System.out.println("Total number of rows = " + count);

		} catch (Exception e) {
			System.out.println("Exception Occurred while inserting ..." + e.getMessage());
//			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void getBookById(int id) {
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			Book book = session.get(Book.class, id);
			System.out.println(book != null ? book : "Book Not Found");
		} catch (Exception e) {
			System.out.println("Error while retrieving ..." + e.getMessage());

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void deleteBook(int id) {
		Session session = null;
		Scanner sc = new Scanner(System.in);
		Transaction tx = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Book book = session.get(Book.class, id);
			System.out.println(book != null ? book : "Invalid Book Id ");
			if (book != null) {
				System.out.println(" Do U want to Delete?");
				char confirm = sc.next().charAt(0);
				if (Character.toUpperCase(confirm) == 'Y') {
					session.remove(book);
					tx.commit();
					System.out.println("Book Deleted SUccessfully");
				} else {
					System.out.println("Book Not deleted");
				}
			}

		} catch (Exception e) {
			System.out.println("Error ..." + e.getMessage());
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public static void updateBook(int id) {
		Session session = null;
		Scanner sc = new Scanner(System.in);
		Transaction tx = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Book book = session.get(Book.class, id);
			if (book == null) {
				System.out.println("Invalid Book Id : " + id);
			} else {
				System.out.println("Existing Book INformation");
				System.out.println(book);
				System.out.println(" Enter new Book Price");
				int newPrice = sc.nextInt();
				System.out.println("Do U want to Update : Y/N");
				char confirm = sc.next().charAt(0);
				if (Character.toUpperCase(confirm) == 'Y') {
					book.setPrice(newPrice);
					session.merge(book);
					tx.commit();
					System.out.println("Book Updated SUccessfully");
				} else {
					System.out.println("Book Not Updated");
				}
			}

		} catch (Exception e) {
			System.out.println("Error ..." + e.getMessage());
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
