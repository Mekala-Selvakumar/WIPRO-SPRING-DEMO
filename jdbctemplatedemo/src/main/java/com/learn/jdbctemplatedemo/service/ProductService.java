package com.learn.jdbctemplatedemo.service;

import com.learn.jdbctemplatedemo.JdbctemplatedemoApplication;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.jdbctemplatedemo.entity.Product;
import com.learn.jdbctemplatedemo.repository.ProductrRepository;

@Service
public class ProductService {

	private final JdbctemplatedemoApplication jdbctemplatedemoApplication;
	private ProductrRepository productRepository;

	public ProductService(ProductrRepository productRepository,
			JdbctemplatedemoApplication jdbctemplatedemoApplication) {
		this.productRepository = productRepository;
		this.jdbctemplatedemoApplication = jdbctemplatedemoApplication;
	}

	public List<Product> getAllProduct() {
		return productRepository.getAllProduct();
	}

	public Product getProductById(int productId) {
		return productRepository.getProductById(productId);
	}

	public boolean addProduct(Product product) {
		int id = product.getProductId();
		Product existingProduct = productRepository.getProductById(id);
		boolean isAdded = false;
		if (existingProduct == null) {
			productRepository.addProduct(product);
			isAdded = true;
		}
		return isAdded;
	}
	
	public  boolean deleteProduct(int productId) {
		Product existingProduct =productRepository.getProductById(productId);
		boolean isDeleted=false;
		if (existingProduct!=null) {
			 productRepository.deleteProduct(productId);
			 isDeleted=true;
		}
		return isDeleted;
	}
}
