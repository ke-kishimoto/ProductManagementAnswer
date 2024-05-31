package com.example.demo.product.entity;


import com.example.demo.category.entity.Category;

public record Product(int id, String productCode, String name, Integer price, String description, Category category) {
}
