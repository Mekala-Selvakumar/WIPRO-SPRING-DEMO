package com.learn.ecartservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecartservice.entity.Cart;
import com.learn.ecartservice.exception.InvalidCartIdException;
import com.learn.ecartservice.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

	private CartService  cartService;
	
	public CartController(CartService  cartService) {
		this.cartService =cartService;
 	}
	
	@PostMapping
	public ResponseEntity<Cart> saveCart(@RequestBody Cart cart){
		return new ResponseEntity<>(cartService.saveCart(cart),HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Cart>> getAllCarts(){
		return ResponseEntity.ok(cartService.getAllCart());
	}
	@GetMapping("/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable Integer cartId)
			throws InvalidCartIdException{
		return ResponseEntity.ok(cartService.getCartById(cartId));
	}
	
	@GetMapping("/email/{emailId}")
	public ResponseEntity<Cart> getCartByEmailId(@PathVariable  String emailId){
		Cart cart = cartService.getCartByEmailId(emailId);
		ResponseEntity<Cart> entity = new  ResponseEntity<Cart>(new Cart()
				,HttpStatus.OK);
		if (cart!=null) {
			entity = new ResponseEntity<Cart>(cart,HttpStatus.OK);
		}
		return entity;
	}
	
	@ExceptionHandler(InvalidCartIdException.class)
	public ResponseEntity<String> invalidCartIdExceptionHandler
	(InvalidCartIdException e){
		return  ResponseEntity.badRequest().body(e.getMessage());
	}
	
	
	
 

}
