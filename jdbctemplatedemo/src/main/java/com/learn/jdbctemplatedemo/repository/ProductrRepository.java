package com.learn.jdbctemplatedemo.repository;

import com.learn.jdbctemplatedemo.JdbctemplatedemoApplication;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.learn.jdbctemplatedemo.entity.Product;

@Repository
public class ProductrRepository {

	private final JdbctemplatedemoApplication jdbctemplatedemoApplication;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	ProductrRepository(JdbctemplatedemoApplication jdbctemplatedemoApplication) {
		this.jdbctemplatedemoApplication = jdbctemplatedemoApplication;
	}

	public int addProduct(Product product) {
		String sql = "insert into product values(?,?,?)";
		return jdbcTemplate.update(sql,product.getProductId(),
				product.getProductName(), product.getPrice());

	}
	

	public List<Product> getAllProduct() {
		String sql = "select * from product";
		// (rs,rowNum) --- row mapper lambda
		// RowMapper functional interface
		// mapRow(ResultSet rs , int rowNUm) - abstract method
//		 manually map each database column  to a java field
//		return jdbcTemplate.query(sql, (rs,rowNum)->{
//		Product product = new Product(rs.getInt("product_id"),
//				rs.getString("product_name"),rs.getInt("price"));
//		return product;
//		});

		// AUtomatically matches database columns to java fields based on their name
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Product.class));
	}

	public void deleteProduct(int productId) {
		jdbcTemplate.update("delete from product where product_id=?", productId);
	}

	public Product getProductById(int productId) {
//		List<Product> products = jdbcTemplate.query("select * from product where product_id=?",
//				new BeanPropertyRowMapper<>(Product.class), productId);
//		return products.isEmpty() ? null : products.get(0);
		
		Product product=null;
		try {
		product = jdbcTemplate.queryForObject("select * from product where product_id=?", 
				new BeanPropertyRowMapper<>(Product.class), productId);
		}
		catch(EmptyResultDataAccessException e) {
			product=null;
    	
		}
		return product;
	}

}
