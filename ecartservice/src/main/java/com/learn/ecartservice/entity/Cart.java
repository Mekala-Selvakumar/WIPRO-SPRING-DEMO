package com.learn.ecartservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
	private Integer cartId;
	private String emailId;
	private List<CartItem> cartItems;

}
