package com.learn.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@GetMapping("/products")
	public String  getProduccts() {
		return "Product retrieved Successfully";
	}
	
	@GetMapping("/admin/products")
	public String  getAdminProduct() {
		return "Product Details - ADMIN";
	}

	 
}
