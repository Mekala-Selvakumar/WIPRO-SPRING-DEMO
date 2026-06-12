package com.learn.reactivebookservice.exceptions;

public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException() {
 	}

	public BookNotFoundException(String message) {
		super(message);
 	}
 
}
