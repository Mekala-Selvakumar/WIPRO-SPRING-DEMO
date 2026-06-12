package com.learn.reactivebookservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.reactivebookservice.entity.Book;
import com.learn.reactivebookservice.exceptions.BookNotFoundException;
import com.learn.reactivebookservice.service.BookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public Flux<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

//	@GetMapping("/books/{id}")
//	public Mono<Book> getBookById(@PathVariable Integer id) {
//		return bookService.getBookById(id);
//	}
	
//	@GetMapping("/books/{id}")
//	public Mono<ResponseEntity<Book>> getBookById(@PathVariable Integer id) {
//		return bookService.getBookById(id).map(ResponseEntity::ok)
//				.defaultIfEmpty(ResponseEntity.notFound().build());
//				
//	}
	
//	@GetMapping("/books/{id}")
//	public Mono<ResponseEntity<?>> getBookById(@PathVariable Integer id) {
//	    return bookService.getBookById(id)
//	            .flatMap(book -> Mono.<ResponseEntity<?>>just(ResponseEntity.ok(book)))
//	            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
//	                                                   .body("Book Not Found")));
//	}
	
	
	@GetMapping("/books/{id}")
	public Mono<ResponseEntity<?>> getBookById(@PathVariable Integer id) {
	    return bookService.getBookById(id)
	            .flatMap(book -> Mono.<ResponseEntity<?>>just(ResponseEntity.ok(book)))
	            .switchIfEmpty( Mono.error(new BookNotFoundException("Book Not Found : " +id)));
	}
	
	@PostMapping("/books")
	public Mono<Book> saveBook(@RequestBody  Book  book) {
		return  bookService.addBook(book);
	}
	@DeleteMapping("/books/{id}")
	public Mono<Void> deleteBook(@PathVariable  Integer id){
		return  bookService.deleteBook(id);
	}
	@PutMapping("/books/{id}")
	public Mono<Book> updateBook(@PathVariable  Integer id,@RequestBody  Book  book) {
		return  bookService.updateBook(id,book);
	}

}
