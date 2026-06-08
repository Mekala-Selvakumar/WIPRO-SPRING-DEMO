package com.learn.jdbctemplatedemo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jdbctemplatedemo.entity.Product;
import com.learn.jdbctemplatedemo.service.ProductService;

@RestController
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct() {
		return ResponseEntity.ok(productService.getAllProduct());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Integer id) {
		Product product = productService.getProductById(id);
		ResponseEntity<?> entity = new ResponseEntity<String>("Product with Id : " + id + " Not Found",
				HttpStatus.NOT_FOUND);
		if (product != null) {
			entity = new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		return entity;

	}
	
	@PostMapping("/products")
	public ResponseEntity<?> addProduct(@RequestBody  Product product){
	 boolean isAdded=	productService.addProduct(product);
	 ResponseEntity<?> entity = new ResponseEntity<String>
	 ("Product Id  already Exists" , HttpStatus.BAD_REQUEST);
	 if (isAdded==true) {
		 entity = new ResponseEntity<Product>(product, HttpStatus.OK);
 	 }
	 return entity;
	 
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable  Integer id){
		 boolean isDeleted=	productService.deleteProduct(id);
		 ResponseEntity<?> entity = new ResponseEntity<String>
		 ("Product Id  Not Exists" , HttpStatus.BAD_REQUEST);
		 if (isDeleted) {
			 entity = new ResponseEntity<String>("Product with Id: " 
		 +id+" deleted successfully", HttpStatus.OK);
	 	 }
		 return entity;
		 
		}
	

}
