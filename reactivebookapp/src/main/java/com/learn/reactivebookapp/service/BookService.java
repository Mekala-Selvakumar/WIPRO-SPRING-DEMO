package com.learn.reactivebookapp.service;

import com.learn.reactivebookapp.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface  BookService {

	public Mono<Book> addBook(Book  book);
	public Flux<Book> getAllBook();
	public Mono<Book> getBookById(Integer id);
	public Mono<Book> updateBook(Integer id, Book book);
	public  Mono<Void> deleteBook(Integer id);
 	
}
