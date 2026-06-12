package com.learn.reactivedemo.controller;

 import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.reactivedemo.model.Product;
import com.learn.reactivedemo.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public Flux<Product>  getProducts(){
		return  productService.getAllProduct();
		
	}
	
	@GetMapping("/products/{id}")
	public Mono<Product> getProductById(@PathVariable  Integer id){
		return productService.getProductById(id);
	}
	
	  //Real Reactie Streaming
	
	@GetMapping(value ="/stream" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Product> streamProducct(){
		
		return Flux.interval(Duration.ofSeconds(2))
				.map(index-> new Product(index.intValue(), "Poduct-"+index,10000.0+index));
		
	}

}
