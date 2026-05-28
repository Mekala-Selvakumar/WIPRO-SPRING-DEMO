package com.demo.config;

import java.beans.Customizer;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.demo.model.Book;
import com.demo.model.Customer;
import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.model.Order;
import com.demo.model.Passport;
import com.demo.model.Person;

public class HibernateUtils {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Properties hibernateProperties = new Properties();
//				hibernateProperties.put("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver");

				hibernateProperties.put("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
				hibernateProperties.put("hibernate.connection.url", "jdbc:oracle:thin:@//localhost:1521/XEPDB1");
				hibernateProperties.put("hibernate.connection.username", "system");
				hibernateProperties.put("hibernate.connection.password", "India12345");
//				Hibernate language translator for a specific  database
				// Different database use , different SQL syntax, Differet data types, different
				// function
				// Hibernate must know - which database SQL should I generate
				hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
				hibernateProperties.put("hibernate.show_sql", "true");
				hibernateProperties.put("hibernate.format_sql", "true");

//				hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
			hibernateProperties.put("hibernate.hbm2ddl.auto","update");

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(hibernateProperties).build();

				MetadataSources metadataSources = new MetadataSources(serviceRegistry);
				metadataSources.addAnnotatedClass(Book.class);
				metadataSources.addAnnotatedClass(Department.class);
				metadataSources.addAnnotatedClass(Employee.class);
				metadataSources.addAnnotatedClass(Customer.class);
				metadataSources.addAnnotatedClass(Order.class);
metadataSources.addAnnotatedClass(Person.class);
metadataSources.addAnnotatedClass(Passport.class);

				Metadata metaData = metadataSources.getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				System.out.println("Exception ocurred while creating SessionFactory  : " + e);
				throw e;
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
