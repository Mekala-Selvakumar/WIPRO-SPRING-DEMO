package com.learn.reactivebookapp.router;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learn.reactivebookapp.handler.BookHandler;

@Configuration
public class BookRouter {

  @Bean
  public RouterFunction<ServerResponse> bookRoutes(BookHandler  handler){
	  return  route(GET("/api/books"),handler::getAllBooks)
			  .andRoute(GET("/api/books/{id}"),handler::getBookById)
			  .andRoute(POST("/api/books"),handler::addBook)
			   .andRoute(PUT("/api/books/{id}"),handler::updateBook)
			   .andRoute(DELETE("/api/books/{id}"),handler::deleteBook);
	     
  }

}
