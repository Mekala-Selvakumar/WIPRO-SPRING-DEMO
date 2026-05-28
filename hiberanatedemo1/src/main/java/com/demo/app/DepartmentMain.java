package com.demo.app;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import com.demo.config.HibernateUtils;
import com.demo.model.Book;
import com.demo.model.Department;

public class DepartmentMain {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Scanner sc = new Scanner(System.in);
		Transaction tx = null;

		try (Session session = sessionFactory.openSession();) {
			tx = session.beginTransaction();
			System.out.println("Enter Department Number : ");
			int departmentId = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Department Name : ");
			String departmentName = sc.nextLine();
			if (departmentName ==null || departmentName.isBlank()) {
				System.out.println("DepartmentName must be  not null");
			}
			else {
			Department department = new Department(departmentId, departmentName);
			session.persist(department);
			tx.commit();
			System.out.println("Department saved");
			}
 
		}
		catch(ConstraintViolationException e ) {
					System.out.println("Error  Department Number already Exist " );	
					System.out.println(e.getMessage());
//					if (tx!=null) {
//						tx.rollback();
//					}
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception Occurred  ..." + e.getMessage());
			e.printStackTrace();
		}
	}

}
