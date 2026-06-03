package com.learn.productapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.productapp.entity.Product;
import com.learn.productapp.exceptions.InvalidPriceException;
import com.learn.productapp.exceptions.ProductNotFoundException;
import com.learn.productapp.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

//	@Autowired
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// example for checked exception
	@Override
	public Product addProduct(Product product) throws InvalidPriceException {
		if (product.getPrice() <= 0) {
			throw new InvalidPriceException("Price should be greater than zero");
		}
		Product createdProduct = productRepository.save(product);
		return createdProduct;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}
	// example for unchecked exception

	@Override
	public Product getProductById(int productId) throws ProductNotFoundException {
//		Optional<Product> op = productRepository.findById(productId);
//		if (op.isEmpty()) {
//			throw new ProductNotFoundException("Product with ProductId : " + productId + " Not Found");
//
//		}
//		return op.get();
		
		return
			productRepository
				.findById(productId)
				.orElseThrow(()->new ProductNotFoundException("Product not found with id : " +productId));
	}

	@Override
	public boolean deleteProduct(int productId) throws ProductNotFoundException {
		boolean isExist = productRepository.existsById(productId);
		if (!isExist) {
			throw new ProductNotFoundException
			("Product with ProductId : " + productId + " Not Found for Delete");
		}
         productRepository.deleteById(productId);
		return true;
	}

	@Override
	public Product updateProduct(int productId, Product product) throws ProductNotFoundException {
 		
		Product existingProduct = productRepository
				.findById(productId)
				.orElseThrow(()->new ProductNotFoundException("Product Not Found with Id : "+productId));
		
		existingProduct.setProductName(product.getProductName());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setPrice(product.getPrice());
		return  productRepository.save(existingProduct);
		
 	}

	@Override
	public List<Product> getProductByCategory(String category) {
 		return  productRepository.findByCategory(category);
	}

	@Override
	public List<Product> getProductBYPriceGreaterThan(int price) {
 		return  productRepository.findByPriceGreaterThan(price);
	}

}
