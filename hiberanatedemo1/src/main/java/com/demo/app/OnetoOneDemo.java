package com.demo.app;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.config.HibernateUtils;
import com.demo.model.Department;
import com.demo.model.Passport;
import com.demo.model.Person;

public class OnetoOneDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Scanner sc = new Scanner(System.in);
		Transaction tx = null;

		try (Session session = sessionFactory.openSession();) {
			tx = session.beginTransaction();
			Person  person = new Person(1, "Vivek");
			Passport passport =new Passport(101, "IND12345");
			passport.setPerson(person);
			person.setPassport(passport);
			
			session.persist(person);
			tx.commit();
			System.out.println("Inserted Successfully");
 
 		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception Occurred  ..." + e.getMessage());
 		}
	}

	}


