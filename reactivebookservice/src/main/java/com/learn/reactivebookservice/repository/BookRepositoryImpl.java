package com.learn.reactivebookservice.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.learn.reactivebookservice.entity.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BookRepositoryImpl implements BookRepository {

	//It is a thread safe 
	private Map<Integer, Book> books = new ConcurrentHashMap<>();
	@Override
	public Flux<Book> findAll() {
 		return Flux.fromIterable(books.values());
	}

	@Override
	public Mono<Book> findById(Integer id) {
 		return Mono.justOrEmpty(books.get(id));
	}

	@Override
	public Mono<Book> save(Book book) {
		books.put(book.getBookId(), book);
 		return Mono.just(book);
	}

	@Override
	public Mono<Void> deleteById(Integer id) {
		books.remove(id);
 		return Mono.empty();
	}

}
