package com.example.demo.category.service;

import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;
import com.example.demo.category.vo.CategoryId;
import com.example.demo.category.vo.CategoryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll().stream().map(categoryRecord -> {
            return new Category(
                    new CategoryId(categoryRecord.id()),
                    new CategoryName(categoryRecord.name())
            );
        }).toList();
    }
}
