package com.springmvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.Product;
import com.springmvc.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public ModelAndView getAllProducts() {
		ModelAndView mav = new ModelAndView("product");
		List<Product> productList = productService.getAllProducts();
		mav.addObject("productList", productList);
		return mav;
	}

	// delete?id=1
//	@GetMapping("/delete")
	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	public ModelAndView deleteProduct(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView("product");
		List<Product> productList = productService.getAllProducts();
		mav.addObject("productList", productList);
		boolean isDeleted = productService.deleteProduct(id);
		if (isDeleted) {
			mav.addObject("message", "Product Deleted Successfully");
		} else {
			mav.addObject("message", "Product deletion failed...Product Not Found");
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/add")
	public ModelAndView addProduct() {
		ModelAndView mav = new ModelAndView("addProduct");
		mav.addObject("product", new Product());
		return mav;
	}

	// @RequestMapping(method = RequestMethod.POST, value="/save")
//	@PostMapping("/save")
//	
//	public ModelAndView  saveProduct( HttpServletRequest request) {
//		int productId =Integer.parseInt( request.getParameter("productId"));
//		String productName = request.getParameter("productName");
////		int price=Integer.parseInt( request.getParameter("price"));
//		Product product = new Product(productId,productName,price);

	@PostMapping("/save")
	public ModelAndView saveProduct(@ModelAttribute Product product) {
		ModelAndView mav = new ModelAndView("addProduct");
		boolean isAdded = productService.addProduct(product);
		if (isAdded) {
			mav.addObject("msg1", "Product Added  Successfully");
		} else {
			mav.addObject("msg2", "Product Id Already Exist..., Unable to Add Product");
		}
		mav.addObject("product", new Product());
		return mav;
	}

}
