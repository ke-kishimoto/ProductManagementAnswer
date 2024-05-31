package com.example.demo.category.repository;

import com.example.demo.category.record.CategoryRecord;

import java.util.List;

public interface CategoryRepository {

    List<CategoryRecord> findAll();
}
