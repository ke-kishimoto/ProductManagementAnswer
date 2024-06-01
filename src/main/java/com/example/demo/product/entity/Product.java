package com.example.demo.product.entity;


import com.example.demo.category.entity.Category;
import com.example.demo.product.vo.*;

public record Product(
        ProductId id,
        ProductCode productCode,
        ProductName name,
        Price price,
        Description description,
        Category category
) { }
