package com.example.demo.product.controller.view;

public record ProductDetailView(
        int id,
        String productCode,
        String name,
        int categoryId,
        int price,
        String description,
        String image
) {
}
