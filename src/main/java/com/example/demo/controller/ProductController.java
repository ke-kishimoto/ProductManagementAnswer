package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
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
	
	@GetMapping("/searchProduct")
	public List<Product> searchProduct(@RequestParam("keyword") String keyword) {
		return productDao.find(keyword);
	}

}
