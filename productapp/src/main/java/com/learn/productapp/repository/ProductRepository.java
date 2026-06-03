package com.learn.productapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.productapp.entity.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer>{

	
	 List<Product>  findByCategory(String category);
	 List<Product> findByPriceGreaterThan(int price);
}
