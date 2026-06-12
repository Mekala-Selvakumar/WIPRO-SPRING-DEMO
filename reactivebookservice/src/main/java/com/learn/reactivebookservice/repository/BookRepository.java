package com.learn.reactivebookservice.repository;

import com.learn.reactivebookservice.entity.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {
	
	public  Flux<Book> findAll();
	public   Mono<Book>  findById(Integer id);
	public  Mono<Book> save(Book book);
	public  Mono<Void> deleteById(Integer id);

}
