package com.learn.ecartservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.ecartservice.entity.Cart;
import com.learn.ecartservice.exception.InvalidCartIdException;
import com.learn.ecartservice.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;

	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public Cart saveCart(Cart cart) {
 		return cartRepository.save(cart);
	}

	@Override
	public Cart getCartByEmailId(String emailId) {
		Cart cart = cartRepository.findByEmailId(emailId).orElse(null);
 		return cart;
	}

	@Override
	public List<Cart> getAllCart() {
 		return cartRepository.findAll();
	}

	@Override
	public Cart getCartById(Integer cartId) throws InvalidCartIdException {
 		return  cartRepository.findById(cartId)
 				.orElseThrow(
 			()->new InvalidCartIdException("Invalid Cart Id : "+cartId)); 
 						
 						
 						
	}

}
