package com.learn.productapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.productapp.ProductappApplication;
import com.learn.productapp.entity.Product;
import com.learn.productapp.exceptions.InvalidPriceException;
import com.learn.productapp.exceptions.ProductNotFoundException;
import com.learn.productapp.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name ="Product Controller",
description ="Operations to related to Product")

@RestController
@RequestMapping("/api/v1")
public class ProductController {

 	private ProductService productService;

	public ProductController(ProductService productService, ProductappApplication productappApplication) {
		this.productService = productService;
 	}

//	@PostMapping("/products")
//	public ResponseEntity<?> addProduct(@RequestBody Product product) {
//		ResponseEntity<?> entity = null;
//		try {
//			Product savedProduct = productService.addProduct(product);
//			entity = new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
//		} catch (InvalidPriceException e) {
//			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//
//		}
//		return entity;
//	}
	
	
	@PostMapping("/products")
	public ResponseEntity<?> addProduct(@RequestBody Product product) throws InvalidPriceException {
		ResponseEntity<?> entity = null;
 			Product savedProduct = productService.addProduct(product);
			entity = new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
 		return entity;
	}

	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts() {
		List<Product> productList = productService.getAllProduct();
//	return ResponseEntity.ok(productList);
		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}
	
	@Operation(
			summary = "Get Product BY Id",
			description = "Returns a product based on the given id")
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProdutById(@PathVariable("id") int productId) throws  ProductNotFoundException{
		Product product =productService.getProductById(productId);
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable int id){
		boolean  isDeleted = productService.deleteProduct(id);
		return ResponseEntity.ok("Product with Id : "+id +" Deleted Successfully");
	}
	
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id ,
			@RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(id, product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	
	@GetMapping("/products/category/{category}")
	public ResponseEntity<?> getAllProductByCategory(@PathVariable String category) {
		List<Product> productList = productService.getProductByCategory(category);
 		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}
	
	@GetMapping("/products/price/{price}")
	public ResponseEntity<?> getAllProductByPriceGreaterThan(@PathVariable int price) {
		List<Product> productList = productService.getProductBYPriceGreaterThan(price);
 		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}
	
	
	
//	@ExceptionHandler(InvalidPriceException.class)
//	public ResponseEntity<String> InvalidPriceExceptionHandler(InvalidPriceException e){
//		return ResponseEntity.badRequest().body(e.getMessage());
//	
//	}

}
