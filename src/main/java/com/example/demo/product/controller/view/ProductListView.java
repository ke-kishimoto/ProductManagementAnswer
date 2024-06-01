package com.example.demo.product.controller.view;

public record ProductListView(
        int id,
        String productCode,
        String name,
        int price,
        String categoryName
) {
}
