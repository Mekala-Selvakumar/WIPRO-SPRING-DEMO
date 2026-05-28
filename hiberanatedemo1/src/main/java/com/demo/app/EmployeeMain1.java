package com.demo.app;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.config.HibernateUtils;
import com.demo.model.Department;
import com.demo.model.Employee;

public class EmployeeMain1 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Scanner sc = new Scanner(System.in);
		Transaction tx = null;

		try (Session session = sessionFactory.openSession();) {
			tx = session.beginTransaction();
			System.out.println("Enter Employee Number : ");
			int employeeId = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Employee Name : ");
			String employeeName = sc.nextLine();
			System.out.println("Enter Employee Salary : ");
			int salary = sc.nextInt();
			System.out.println("Enter Department Number : ");
			int departmentId = sc.nextInt();
			Department dept = session.find(Department.class, departmentId);
        	if (dept == null) {
 				System.out.println("Department Id  Not Exist : " + departmentId);
				}
        	else {
        		Employee  emp1 = new Employee(employeeId, employeeName, salary, dept);
         		session.persist(emp1);
         		tx.commit();
        		System.out.println("New Employee Created");

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


