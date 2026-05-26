package com.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springmvc.model.Product;

@Component
public class ProductDAO {
	public static List<Product> productList;
	public ProductDAO() {
		productList = new ArrayList<>();
		productList.add(new Product(1,"Laptop",90000));	
		productList.add(new Product(2,"SmartPhone",50000));
		productList.add(new Product(3,"Tablet",40000));
		productList.add(new Product(4,"Monitor",10000));
		productList.add(new Product(5,"Keyboard",2000));
	}
	public List<Product>  getAllProducts(){
		return  productList;
	}
	public Product  getProductById(int id) {
		return  productList.stream()
				.filter(p->p.getProductId()==id).findFirst().orElse(null);
	}
	public  void  addProduct(Product product) {
		productList.add(product);
	}
	public void deleteProduct(int id) {
		productList.removeIf(p->p.getProductId()==id);
	}
	public boolean isProductExist(int id) {
		Product product =getProductById(id);
		return product!=null;
	}
	public boolean updateProduct(Product product, int id) {
		boolean isUpdated=false;
		Product existingProduct = getProductById(id);
		if (existingProduct!=null) {
			existingProduct.setProductName(product.getProductName());
			existingProduct.setPrice(product.getPrice());
			isUpdated=true;
		}
		return isUpdated;
	}
}
