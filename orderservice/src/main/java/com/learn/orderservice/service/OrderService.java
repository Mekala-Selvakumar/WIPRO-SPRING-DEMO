package com.learn.orderservice.service;

import org.springframework.stereotype.Service;

import com.learn.orderservice.client.ProductClient;
import com.learn.orderservice.model.Product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

	private final ProductClient productClient;

	public OrderService(ProductClient productClient) {
		this.productClient = productClient;
	}

	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackProduct")
	public Product createOrder(Integer productId) {
		Product product = productClient.getProductById(productId);
		return product;
	}

	public Product fallbackProduct(Integer id, Exception ex) {
		Product product = new Product();
		product.setProductName("Default Product");
		return product;

//		return "Product Service is temporarily unavailable";
	}
}
