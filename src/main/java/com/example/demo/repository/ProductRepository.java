package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.record.ProductRecord;

import java.util.List;

public interface ProductRepository {

    List<ProductRecord> find(String keyword);

    ProductRecord findById(int id);

    int insert(ProductRecord p);

    int delete(int id);

    int update(ProductRecord p);

    Product findByProductCode(String productCode, int id);
}
