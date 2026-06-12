package com.learn.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.productservice.model.Product;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@GetMapping("/products/{id}")
	public ResponseEntity<String> getProduct(@PathVariable Integer id) {
		return ResponseEntity.ok("Laptop");
	}

	@GetMapping("/products/id/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
         Product product = new Product();
         product.setProductId(101);
         product.setProductName("Laptop");
         product.setCategory("Electronics");
         product.setPrice(90000);
         
		return ResponseEntity.ok(product);
	}

}
