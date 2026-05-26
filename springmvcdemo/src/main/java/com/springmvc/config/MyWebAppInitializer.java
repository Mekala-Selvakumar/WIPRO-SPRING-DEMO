package com.springmvc.config;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//Instead web.xml, In java based configuration -  AbstractAnnotationConfigDispatcherServletInitializer
// spring will detect  this class  because it extends AbstractAnnotationConfigDispatcherServletInitializer
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
// Root Application Context Configuration Classes
	
	//which configuration classes should be loaded into the root IOC container
	//Database configuration
	//spring MVC+Hibernate application we can configure datasource,sessionFactory, transactionmanager etc here
	//bakend configuration
	@Override
	protected Class<?> @Nullable [] getRootConfigClasses() {
 		return null;
	}

	// which java configuration class contains  MVC configuration
	@Override
	protected Class<?> @Nullable [] getServletConfigClasses() {
 		return  new Class[] {AppConfig.class};
	}

	//  which url patterns will be handled by DispatcherServlet
	//    /- means all request
	//   /api/*  - all requests starting with /api/    /api/login  /api/register
	@Override
	protected String[] getServletMappings() {
 		return  new String[] {"/"};  
 		//  /home, /login , /products,  /api/users

	}
// maps requests to the spring MVC front controller (DispatcherServlet)
}

/*  In Web Application, Spring Created two IOC containers
 * 1. Root Application Context
 *      - creaated by ContextLoaderListener
 *       - shared across the entire application
 *       - used for backend/common configuration
 *  2. DispatcherServlet Application Context
 *      - Created by DispatcherServlet
 *      - Used only for  web/MVC-related components
 */
 
