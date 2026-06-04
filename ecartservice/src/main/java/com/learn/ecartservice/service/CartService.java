package com.learn.ecartservice.service;

import java.util.List;

import com.learn.ecartservice.entity.Cart;
import com.learn.ecartservice.exception.InvalidCartIdException;

public interface CartService {
	
	public Cart saveCart(Cart cart);
	public Cart getCartByEmailId(String emailId);
	public List<Cart> getAllCart();
	public Cart  getCartById(Integer cartId) throws InvalidCartIdException;

}
