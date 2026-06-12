package com.learn.reactivedemo.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.learn.reactivedemo.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	
	public Mono<Product> getProductById(Integer id){
		Product product = new Product(id, "Laptop", 90000.0);
//		return Mono.just(product);
		//Response arrives after 10 seconds  = Thread not blocked
		//Traitional  MVC - Thread.sleep -Thread Blocked
		return Mono.just(product).delayElement(Duration.ofSeconds(10));
	}
	
	public Flux<Product> getAllProduct(){
		return Flux.just(
				new Product(1,"Laptop",90000.0),
				new Product(2,"Mobile",20000.0),
				new Product(3, "TV",40000.0)
				
				);
	}
}

