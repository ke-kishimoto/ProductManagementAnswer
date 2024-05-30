package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> find(String keyword) {
        return productRepository.find(keyword);
    }

    public Product findById(int id) {
        return productRepository.findById(id);
    }

    public int insert(Product p) {
        return productRepository.insert(p);
    }

    public int delete(int id) {
        return productRepository.delete(id);
    }

    public int update(Product p) {
        return productRepository.update(p);
    }

    public Product findByProductCode(String productCode, int id) {
        return productRepository.findByProductCode(productCode, id);
    }
}
