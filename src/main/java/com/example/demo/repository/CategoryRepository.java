package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.record.CategoryRecord;

import java.util.List;

public interface CategoryRepository {

    List<CategoryRecord> findAll();
}
