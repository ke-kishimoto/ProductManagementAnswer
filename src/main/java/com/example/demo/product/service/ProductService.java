package com.example.demo.product.service;

import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.record.ProductRecord;
import com.example.demo.product.repository.record.ProductRecordFactory;
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

    public int insert(Product product) {
        var productRecord = ProductRecordFactory.createInsertProductRecord(
                product.productCode(),
                product.name(),
                product.category().id(),
                product.price(),
                product.description()
        );
        return productRepository.insert(productRecord);
    }

    public int delete(int id) {
        return productRepository.delete(id);
    }

    public int update(Product product) {
        var productRecord = ProductRecordFactory.createUpdateProductRecord(
                product.id(),
                product.productCode(),
                product.name(),
                product.category().id(),
                product.price(),
                product.description()
        );
        return productRepository.update(productRecord);
    }

    public Product findByProductCode(String productCode, int id) {
        return productRepository.findByProductCode(productCode, id);
    }

}
