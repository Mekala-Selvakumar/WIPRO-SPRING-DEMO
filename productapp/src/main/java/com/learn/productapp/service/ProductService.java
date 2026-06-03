package com.learn.productapp.service;

import java.util.List;

import com.learn.productapp.entity.Product;
import com.learn.productapp.exceptions.InvalidPriceException;
import com.learn.productapp.exceptions.ProductNotFoundException;

public interface ProductService {
	public  Product addProduct(Product product) throws InvalidPriceException ;
	public List<Product> getAllProduct();
	public Product getProductById(int productId) throws  ProductNotFoundException;
	public boolean  deleteProduct(int productId) throws ProductNotFoundException;
    public Product updateProduct(int productId,Product product) throws ProductNotFoundException;
  public List<Product> getProductByCategory(String category);
  public List<Product> getProductBYPriceGreaterThan(int price);

}
