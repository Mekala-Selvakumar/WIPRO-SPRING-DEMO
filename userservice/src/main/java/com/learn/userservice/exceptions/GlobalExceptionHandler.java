package com.learn.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(EmailIdAlreadyExistsException.class)
	  public ResponseEntity<String> handleEmailIdAlreadyExistsException(EmailIdAlreadyExistsException  e){
		 return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	 }
	 
	
	 
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	  public ResponseEntity<String> handleException(MethodArgumentNotValidException e){
//		 return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	
	 String  errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
	  return ResponseEntity.badRequest().body(errorMessage);
	 }
	 

}
