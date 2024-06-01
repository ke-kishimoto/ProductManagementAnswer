package com.example.demo.product.repository;

import com.example.demo.product.repository.record.ProductRecord;

import java.util.List;

public interface ProductRepository {

    List<ProductRecord> find(String keyword);

    ProductRecord findById(int id);

    int insert(ProductRecord p);

    int delete(int id);

    int update(ProductRecord p);

    ProductRecord findByProductCode(String productCode);
}
