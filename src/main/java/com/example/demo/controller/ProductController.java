package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/getProductList")
	public List<Product> getProductList() {
		return productRepository.find("");
	}
	
	@GetMapping("/getProduct")
	public Product getProduct(@RequestParam("id") int id) {
		return productRepository.findById(id);
	}
	
	@GetMapping("/searchProduct")
	public List<Product> searchProduct(@RequestParam("keyword") String keyword) {
		return productRepository.find(keyword);
	}
	
	@GetMapping("/getCategoryList")
	public List<Category> getCategoryList() {
		return categoryService.findAll();
	}
	
	@PostMapping("/registerProduct")
	public int registerProduct(@RequestBody Product product) {
		return productRepository.insert(product);
	}
	
	@PostMapping("/deleteProduct")
	public int deleteProduct(@RequestBody int id) {
		return productRepository.delete(id);
	}

}
