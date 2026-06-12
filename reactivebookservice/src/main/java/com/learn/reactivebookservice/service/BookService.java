package com.learn.reactivebookservice.service;

import org.springframework.stereotype.Service;

import com.learn.reactivebookservice.entity.Book;
import com.learn.reactivebookservice.repository.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Flux<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Mono<Book> getBookById(Integer id) {
		return bookRepository.findById(id);
	}

	public Mono<Book> addBook(Book book) {
		return bookRepository.save(book);
	}
	
	public Mono<Void> deleteBook(Integer id){
		return bookRepository.deleteById(id);
	}
	
	public Mono<Book> updateBook(Integer id, Book book){
		return  bookRepository.findById(id).
				flatMap(
					existing -> {
					existing.setBookTitle(book.getBookTitle());
					existing.setAuthor(book.getAuthor());
					existing.setPrice(book.getPrice());
					return  bookRepository.save(book);  
					});
				
	}
}