 package com.learn.ecartservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learn.ecartservice.entity.Cart;

@Repository
public interface CartRepository  extends  MongoRepository<Cart, Integer>{
	
	public Optional<Cart> findByEmailId(String emailId);

}
