package com.learn.orderservice.controler;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learn.orderservice.OrderserviceApplication;
import com.learn.orderservice.model.Product;
import com.learn.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	private final OrderserviceApplication orderserviceApplication;

	@Autowired
	private RestTemplate  restTemplate;
	
	@Autowired
	private OrderService   orderService;

	OrderController(OrderserviceApplication orderserviceApplication) {
		this.orderserviceApplication = orderserviceApplication;
	}
	
	 @GetMapping("/orders/{id}")
	 public ResponseEntity<String>  getOrder(@PathVariable Integer id){
		 //hardcoded URL
		 String  product =restTemplate
				 .getForObject("http://localhost:9095/api/v1/products/1", String.class);
		 return ResponseEntity.ok("Order Created with Produt : " +product);
	 }
	 
	 @GetMapping("/orders/id/{id}")
	 public ResponseEntity<Product>  getOrderFeign(@PathVariable Integer id){
		 //hardcoded URLorderserviceApplication
		 Product  product = orderService.createOrder(id);
		 log.info("Fetching Order : {}",id)''
		 return ResponseEntity.ok(product);
	 }
 

	// @GetMapping("/orders/{id}")
	// public ResponseEntity<String>  getOrder(@PathVariable Integer id){
//		 return ResponseEntity.ok("Order Created");
	// }
	  
 
 
 
}
