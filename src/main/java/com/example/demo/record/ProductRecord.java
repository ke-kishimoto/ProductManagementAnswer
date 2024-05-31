package com.example.demo.record;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import java.sql.Timestamp;

public record ProductRecord(int id,
                            String productCode,
                            String name,
                            int categoryId,
                            String categoryName,
                            Integer price,
                            String imagePath,
                            String description,
                            Timestamp createdAt,
                            Timestamp updatedAt) {
    public Product toProduct() {
        return new Product(
                this.id,
                this.productCode,
                this.name,
                this.price,
                this.description,
                new Category(this.categoryId, this.categoryName)
        );
    }
}
