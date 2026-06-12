package com.learn.reactivebookapp.service;

import org.springframework.stereotype.Service;

import com.learn.reactivebookapp.exceptions.BookNotFoundException;
import com.learn.reactivebookapp.model.Book;
import com.learn.reactivebookapp.repository.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

	private  BookRepository  bookRepository;
	
	public BookServiceImpl(BookRepository  bookRepository) {
		this.bookRepository=bookRepository;
	}
	@Override
	public Mono<Book> addBook(Book book) {
 		return  bookRepository.save(book);
	}

	@Override
	public Flux<Book> getAllBook() {
 		return bookRepository.findAll();
	}

	@Override
	public Mono<Book> getBookById(Integer id) {
 		return  bookRepository.findById(id)
 				.switchIfEmpty(Mono.error(new  BookNotFoundException("Book Not Found : "+id)));
	}

	@Override
	public Mono<Book> updateBook(Integer id, Book book) {
 		return  bookRepository.findById(id)
 				.flatMap(existingBook-> {
 				existingBook.setBookTitle(book.getBookTitle());
 				existingBook.setAuthor(book.getAuthor());
 				existingBook.setPrice(book.getPrice());
 				return  bookRepository.save(existingBook);
 				})
 				.switchIfEmpty(Mono.error(new BookNotFoundException("Book Not Found with Id : "+id)));
 				}

	@Override
	public Mono<Void> deleteBook(Integer id) {
 		return bookRepository.findById(id)
 				.switchIfEmpty(Mono.error(new BookNotFoundException("Book Not Found with Id : "+id)))
 				.flatMap(bookRepository::delete);
 				
 				
// 				bookRepository.deleteById(id);
	}

}
