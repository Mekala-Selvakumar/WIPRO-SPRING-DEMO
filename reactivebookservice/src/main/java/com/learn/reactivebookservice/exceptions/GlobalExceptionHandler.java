package com.learn.reactivebookservice.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learn.reactivebookservice.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
//	@ExceptionHandler(BookNotFoundException.class)
//	public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex){
//		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//	}
	
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex){
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				 new ErrorResponse(ex.getMessage(), LocalDateTime.now()));
	}

}
