package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

@RestController
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	CategoryDao categoryDao;
	
	@GetMapping("/getProductList")
	public List<Product> getProductList() {
		return productDao.find("");
	}
	
	@GetMapping("/getProduct")
	public Product getProduct(@RequestParam("id") int id) {
		return productDao.findById(id);
	}
	
	@GetMapping("/searchProduct")
	public List<Product> searchProduct(@RequestParam("keyword") String keyword) {
		return productDao.find(keyword);
	}
	
	@GetMapping("/getCategoryList")
	public List<Category> getCategoryList() {
		return categoryDao.findAll();
	}
	
	@PostMapping("/registerProduct")
	public int registerProduct(@RequestBody Product product) {
		return productDao.insert(product);
	}
	
	@PostMapping("/deleteProduct")
	public int deleteProduct(@RequestBody int id) {
		return productDao.delete(id);
	}

}
