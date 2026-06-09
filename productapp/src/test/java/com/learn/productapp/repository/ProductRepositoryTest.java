package com.learn.productapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import com.learn.productapp.entity.Product;

@DataJpaTest // by default Original database is replaced by H2 database
//it configures an in-memory, embedded database - H2
@AutoConfigureTestDatabase(replace = Replace.NONE) // it take the original database. It force Spring Boot to use the
													// actual database configured in the application.properties

class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	private Product product1;
	private Product product2;

	@BeforeEach
	void setUp() throws Exception {
		product1 = new Product("Crayons", "Stationary", 120);
		product2 = new Product("Water Color", "Stationary", 200);
	}

	@AfterEach
	void tearDown() throws Exception {
		product1 = null;
		product2 = null;
	}

	@Test
	public void testSaveSuccess() {
		Product createdProduct = productRepository.save(product1);
		assertThat(createdProduct).isNotNull();
		assertEquals("Crayons", createdProduct.getProductName());
		assertThat(createdProduct.getProductId()).isNotNull();
		assertThat(createdProduct.getPrice()).isEqualTo(120);

		// cleanup
		productRepository.deleteById(createdProduct.getProductId());
	}

	@Test
	void testfindByIdSuccess() {
		Product createdProduct = productRepository.save(product1);
		Integer id = createdProduct.getProductId();

		Optional<Product> optional = productRepository.findById(id);
//       assertEquals(createdProduct, optional.get());
		assertTrue(optional.isPresent());
	}

	@Test
	void testfindByIdNotFound() {
		Optional<Product> optional = productRepository.findById(9999);
		assertTrue(optional.isEmpty());
	}

	@Test
	public void testFindAll() {
		Product saveProduct1 = productRepository.save(product1);
		Product saveProduct2 = productRepository.save(product2);
		List<Product> productList = productRepository.findAll();
		List<Product> products = List.of(product1, product2);
		assertThat(productList).containsAll(products);

		// Product class must implement the hashcode and equals method
		// assertIterableEquals(products, productList);
		// it compare the iterabke objects for equality, element by element

	}

	@Test
	void testFindByCategory() {
		Product saveProduct1 = productRepository.save(product1);
		Product saveProduct2 = productRepository.save(product2);
		List<Product> productList = productRepository.findByCategory("Stationary");
		List<Product> products = List.of(product1, product2);
		assertThat(productList).containsAll(products);
	}

	@Test
	void testFindByPriceGreaterThan() {
		Product saveProduct1 = productRepository.save(product1);
		Product saveProduct2 = productRepository.save(product2);
		List<Product> productList = productRepository.findByPriceGreaterThan(150);
		List<Product> products = List.of(product2);
		assertThat(productList).containsAll(products);
		
 	}

	@Test
	void testDeleteByIdSuccess() {
		Product saveProduct1 = productRepository.save(product1);
		Integer id = saveProduct1.getProductId();
		productRepository.deleteById(id);
		Optional<Product> optional = productRepository.findById(id);
		assertThat(optional.isEmpty());
	}
	
	@Test
	void testExistById() {
		Product  saveProduct1 = productRepository.save(product1);
		boolean result = productRepository.existsById(saveProduct1.getProductId());
		assertTrue(result);

	}
	
	@Test
	void testExistByIdFailure() {
 		boolean result = productRepository.existsById(9999);
		assertFalse(result);

	}

}
