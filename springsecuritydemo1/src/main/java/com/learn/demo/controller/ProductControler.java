package com.learn.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControler {

 @GetMapping("/products")
 public String getProducct() {
	 return "Product Retrieved Successfully";
 }

}
