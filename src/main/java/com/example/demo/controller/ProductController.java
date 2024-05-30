package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/getProductList")
	public List<Product> getProductList() {
		return productService.find("");
	}
	
	@GetMapping("/getProduct")
	public Product getProduct(@RequestParam("id") int id) {
		return productService.findById(id);
	}
	
	@GetMapping("/searchProduct")
	public List<Product> searchProduct(@RequestParam("keyword") String keyword) {
		return productService.find(keyword);
	}
	
	@GetMapping("/getCategoryList")
	public List<Category> getCategoryList() {
		return categoryService.findAll();
	}
	
	@PostMapping("/registerProduct")
	public int registerProduct(@RequestBody Product product) {
		return productService.insert(product);
	}
	
	@PostMapping("/deleteProduct")
	public int deleteProduct(@RequestBody int id) {
		return productService.delete(id);
	}

}
