package com.example.demo.product.repository.record;

import com.example.demo.category.entity.Category;
import com.example.demo.category.vo.CategoryId;
import com.example.demo.category.vo.CategoryName;
import com.example.demo.product.entity.Product;
import com.example.demo.product.vo.*;

import java.sql.Timestamp;

public record ProductRecord(
        int id,
        String productCode,
        String name,
        int categoryId,
        String categoryName,
        Integer price,
        String imagePath,
        String description,
        Timestamp createdAt,
        Timestamp updatedAt
) {
    public Product toProduct() {
        return new Product(
                new ProductId(this.id),
                new ProductCode(this.productCode),
                new ProductName(this.name),
                new Price(this.price),
                new Description(this.description),
                new Category(
                        new CategoryId(this.categoryId),
                        new CategoryName(this.categoryName)
                )
        );
    }
}