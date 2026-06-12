package com.learn.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learn.orderservice.model.Product;

@FeignClient(name="productClient", url = "http://localhost:9095")
//@FeignClient(name = "PRODUCTSERVICE")
public interface ProductClient {

	 @GetMapping("/api/v1/products/id/{id}")
	 Product  getProductById(@PathVariable("id") Integer id);

}
