package com.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.springmvc")
@EnableWebMvc
public class AppConfig {
	// home
	// "/WEB-INF/views/" +"home"+".jsp"  --- > /WEN-INF/views/home.jsp
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver =
				new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		return viewResolver;
	}

}
