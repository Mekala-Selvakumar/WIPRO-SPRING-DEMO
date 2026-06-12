package com.learn.reactivebookapp.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learn.reactivebookapp.model.Book;
import com.learn.reactivebookapp.service.BookService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookHandler {

	private final BookService bookService;

	public Mono<ServerResponse> getAllBooks(ServerRequest request) {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(bookService.getAllBook(), Book.class);
	}
	
	public Mono<ServerResponse> getBookById(ServerRequest  request){
		Integer  id =Integer.valueOf(request.pathVariable("id"));
		return  bookService.getBookById(id)
				.flatMap(book->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
						.bodyValue(book));
	}
	
	public Mono<ServerResponse> addBook(ServerRequest request){
		return  request.bodyToMono(Book.class)
				 .flatMap(bookService::addBook)
				 .flatMap(book ->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
						 .bodyValue(book)
						);
	}
	
	public Mono<ServerResponse> updateBook(ServerRequest request){
		Integer  id =Integer.valueOf(request.pathVariable("id"));
		return  request.bodyToMono(Book.class)
				.flatMap(book-> bookService.updateBook(id, book))
				.flatMap(updatedBook->ServerResponse.ok().bodyValue(updatedBook));
	}
	
	public Mono<ServerResponse> deleteBook(ServerRequest request){
		Integer  id =Integer.valueOf(request.pathVariable("id"));
		return  bookService.deleteBook(id).then(ServerResponse.noContent().build());
	}

}
