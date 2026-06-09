package com.learn.productapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learn.productapp.entity.Product;
import com.learn.productapp.exceptions.InvalidPriceException;
import com.learn.productapp.exceptions.ProductNotFoundException;
import com.learn.productapp.repository.ProductRepository;

@ExtendWith(MockitoExtension.class) // Activates Mockito support
class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	Product product1;
	Product product2;
	Product product3;

	@BeforeEach
	void setUp() throws Exception {
		product1 = new Product(1,"Crayons", "Stationary", 120);
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
	void testGetAllProduct() {
		List<Product> productList = List.of(product1, product2, product3);
		when(productRepository.findAll()).thenReturn(productList);
		List<Product> products = productService.getAllProduct();
//		assertIterableEquals(productList, products);
		assertIterableEquals(productList, products, "The fetched Product list does not match the expected data");

	}

	@Test
	void testGetProductByIdSucess() {
		Optional<Product> optional = Optional.of(product1);
		when(productRepository.findById(anyInt())).thenReturn(optional);
		Product returnProduct = productService.getProductById(1);
		assertNotNull(returnProduct);

		assertEquals(product1, returnProduct);
	}

	@Test
	void testGetProductByIdFailure() {
		Integer productId =999;
 		when (productRepository.findById(anyInt())).thenReturn(Optional.empty());
      ProductNotFoundException exception =  assertThrows(ProductNotFoundException.class,
    		 ()->productService.getProductById(productId), 
    		 "Expected getProductId to throw, but it didn't");
      assertEquals("Product not found with id : "+productId , exception.getMessage() );
 	}

	@Test
	void testAddProdutSuccess() throws InvalidPriceException {
		Product p1 = new Product("Crayons", "Stationary", 120);
		when(productRepository.save(p1)).thenReturn(product1);
		Product createdProduct = productService.addProduct(p1);
		assertNotNull(createdProduct);
		assertEquals(1, createdProduct.getProductId());
		assertEquals("Crayons", createdProduct.getProductName());
		assertEquals(product1, createdProduct);
	}

	@Test
	void testAddProdutFailure() throws InvalidPriceException {
		Product p1 = new Product("Crayons", "Stationary", -5);

		InvalidPriceException exception = assertThrows(InvalidPriceException.class,
				() -> productService.addProduct(p1));

		assertEquals("Price should be greater than zero", exception.getMessage());
		verify(productRepository, never()).save(any());
	}

	@Test
	void testDeleteProductSuccess() {
		when(productRepository.existsById(1991)).thenReturn(true);
		doNothing().when(productRepository).deleteById(1991);
		boolean result = productService.deleteProduct(1991);

		assertTrue(result);
		verify(productRepository).deleteById(1991);

	}

	@Test
	void shouldThrowExceptionWhenDeletingNonExistingProduct() {
		when(productRepository.existsById(anyInt())).thenReturn(false);
		
		ProductNotFoundException  ex = assertThrows(ProductNotFoundException.class,
				()->productService.deleteProduct(1000));
        assertEquals("Product with ProductId : 1000 Not Found for Delete",  ex.getMessage());
		 		verify(productRepository, never()).deleteById(anyInt());

	}

//	@Test
//	void testUpdateProduct() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetProductByCategory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetProductBYPriceGreaterThan() {
//		fail("Not yet implemented");
//	}

}
