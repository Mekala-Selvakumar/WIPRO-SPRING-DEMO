package com.learn.productapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.learn.productapp.entity.Product;
import com.learn.productapp.exceptions.InvalidPriceException;
import com.learn.productapp.exceptions.ProductNotFoundException;
import com.learn.productapp.service.ProductService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	Product product1;
	Product product2;
	Product product3;

	@BeforeEach
	void setUp() throws Exception {
		product1 = new Product(1, "Crayons", "Stationary", 120);
		product2 = new Product("Water Color", "Stationary", 200);
		product3 = new Product("Nokia-Mobile", "Electronics", 6000);
	}

	@AfterEach
	void tearDown() throws Exception {
		product1 = null;
		product2 = null;
		product3 = null;
	}

	@Test
	void testAddProduct() throws Exception {
		Product p1 = new Product("Crayons", "Stationary", 120);

		String requestJson = new ObjectMapper().writeValueAsString(p1);
		String resultStr = new ObjectMapper().writeValueAsString(product1);
		when(productService.addProduct(any(Product.class))).thenReturn(product1);
		mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().json(resultStr)).andDo(MockMvcResultHandlers.print());

	}

	@Test
	void testAddProdutInvalidPrice() throws Exception {
		Product product = new Product("Water Color", "Stationary", -100);
		when(productService.addProduct(any(Product.class)))
				.thenThrow(new InvalidPriceException("Price should be greater than zero"));

		String requestJson = new ObjectMapper().writeValueAsString(product);

		mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string("Price should be greater than zero"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void testGetAllProducts() throws Exception {
		List<Product> products = List.of(new Product(1, "Pen", "Stationary", 50),
				new Product(2, "Color Book", "Stationary", 100));
		String productsStr = new ObjectMapper().writeValueAsString(products);
		when(productService.getAllProduct()).thenReturn(products);
		mockMvc.perform(get("/api/v1/products")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.content().json(productsStr)).andDo(MockMvcResultHandlers.print());
	}

	@Test
	void testGetProdutById() throws Exception {
		when(productService.getProductById(1)).thenReturn(product1);
		String productsStr = new ObjectMapper().writeValueAsString(product1);
		mockMvc.perform(get("/api/v1/products/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(productsStr));
	}
	
	@Test
	void testGetProdutByIdFailure() throws Exception {
		when(productService.getProductById(14567)).thenThrow(new ProductNotFoundException("Product with id 14567 not found"));
 		mockMvc.perform(get("/api/v1/products/14567")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content()
						.string("Product with id 14567 not found"));
	}

	@Test
	void testDeleteProductById() throws Exception {
		when(productService.deleteProduct(anyInt())).thenReturn(true);
 		mockMvc.perform(delete("/api/v1/products/1")).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content()
				.string("Product with Id : 1 Deleted Successfully"));
 	}
	
	
	@Test
	void testDeleteProductByIdFailure() throws Exception {
		when(productService.deleteProduct(anyInt()))
		.thenThrow(new ProductNotFoundException("Product with ProductId : 14567 Not Found for Delete"));
 		mockMvc.perform(delete("/api/v1/products/14567")).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.content()
				.string("Product with ProductId : 14567 Not Found for Delete"));
 	}

//	@Test
//	void testUpdateProduct() {
//		fail("Not yet implemented");
//	}

	@Test
	void testGetAllProductByCategory() throws Exception {
		List<Product> products = List.of(new Product(1, "Pen", "Stationary", 50),
				new Product(2, "Color Book", "Stationary", 100));
		String productsStr = new ObjectMapper().writeValueAsString(products);
		
		when(productService.getProductByCategory("Stationary")).thenReturn(products);
		
		mockMvc.perform(get("/api/v1/products/category/Stationary"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.content().json(productsStr))
				.andDo(MockMvcResultHandlers.print());
	
 	}

	@Test
	void testGetAllProductByPriceGreaterThan() throws Exception {
		List<Product> products = List.of(new Product(1, "Pen", "Stationary", 50),
				new Product(2, "Color Book", "Stationary", 100),
				new Product(3,"Color Chart Set","Stationary",250));
 		String productsStr = new ObjectMapper().writeValueAsString(products);
		when(productService.getProductBYPriceGreaterThan(40)).thenReturn(products);
		mockMvc.perform(get("/api/v1/products/price/40"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3))
				.andExpect(MockMvcResultMatchers.content().json(productsStr))
				.andDo(MockMvcResultHandlers.print());
	
 	}

}
