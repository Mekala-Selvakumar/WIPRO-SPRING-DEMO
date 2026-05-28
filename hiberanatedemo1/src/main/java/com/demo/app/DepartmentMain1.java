package com.demo.app;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.config.HibernateUtils;
import com.demo.model.Department;

public class DepartmentMain1 {

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
			if (departmentName == null || departmentName.isBlank()) {
				System.out.println("DepartmentName must be  not null");
			} else {
				Department department = new Department(departmentId, departmentName);
				Department dept = session.find(Department.class, departmentId);
				if (dept == null) {
					session.persist(department);
					tx.commit();
					System.out.println("Department saved");
				} else {
					System.out.println("Department Id already Exist : " + dept);
				}

			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception Occurred  ..." + e.getMessage());
			e.printStackTrace();
		}
	}

}
