package com.example.demo.repository;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> find(String keyword);

    Product findById(int id);

    int insert(Product p);

    int delete(int id);

    int update(Product p);

    Product findByProductCode(String productCode, int id);
}
