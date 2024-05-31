package com.example.demo.entity;


public record Product(int id, String productCode, String name, Integer price, String description, Category category) {
}
