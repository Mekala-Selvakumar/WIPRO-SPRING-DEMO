package com.learn.productapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidPriceException.class)
	public ResponseEntity<String> InvalidPriceExceptionHandler(InvalidPriceException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String>  productNotFoundExceptionHandler(ProductNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	
	}

}
