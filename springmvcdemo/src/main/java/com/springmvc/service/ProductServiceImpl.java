package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.ProductDAO;
import com.springmvc.model.Product;

@Service
public class ProductServiceImpl  implements  ProductService{
	
//	@Autowired
//	private ProductDAO  productDao;
	
 	private ProductDAO  productDao;
 	
 	public ProductServiceImpl(ProductDAO productDao) {
 	     this.productDao = productDao;
 	}

	@Override
	public List<Product> getAllProducts() {
 		return  productDao.getAllProducts();
	}

	@Override
	public Product getProductById(int id) {
 		return productDao.getProductById(id);
	}

	@Override
	public boolean addProduct(Product product) {
		 boolean isAdded=false;
		 Product existingProduct = productDao.getProductById(product.getProductId());
		 if (existingProduct==null) {
			 productDao.addProduct(product);
			 isAdded=true;
		 }
 		return  isAdded;
	}

	@Override
	public boolean deleteProduct(int id) {
 		boolean isDeleted=false;
		 Product existingProduct = productDao.getProductById(id);
		 if (existingProduct!=null) {
			 productDao.deleteProduct(id);
			 isDeleted=true;
		 }
  		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product, int id) {
		boolean isUpdate =false;
		boolean isExist = productDao.isProductExist(id);
		if (isExist) {
			productDao.updateProduct(product, id);
			isUpdate=true;
		}
  		return isUpdate;
	}
	

}
