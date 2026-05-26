package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	public Product getProductById(int id);
	public  boolean addProduct(Product product);
	public boolean deleteProduct(int id);
	public boolean updateProduct(Product product, int id);
	

}
