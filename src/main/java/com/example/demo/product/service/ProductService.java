package com.example.demo.product.service;

import com.example.demo.product.entity.Product;
import com.example.demo.product.record.ProductRecord;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> find(String keyword) {
        return productRepository.find(keyword).stream().map(ProductRecord::toProduct).toList();
    }

    public Product findById(int id) {
        return productRepository.findById(id).toProduct();
    }

    public int insert(Product p) {
        return productRepository.insert(toProductRecord(p));
    }

    public int delete(int id) {
        return productRepository.delete(id);
    }

    public int update(Product p) {
        return productRepository.update(toProductRecord(p));
    }

    public Product findByProductCode(String productCode, int id) {
        return productRepository.findByProductCode(productCode, id);
    }

    private ProductRecord toProductRecord(Product p) {
        return new ProductRecord(
                p.id(),
                p.productCode(),
                p.name(),
                p.category().id(),
                p.category().name(),
                p.price(),
                null,
                p.description(),
                null,
                null
        );
    }
}
