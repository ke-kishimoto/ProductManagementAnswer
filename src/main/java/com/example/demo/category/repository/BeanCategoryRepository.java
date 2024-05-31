package com.example.demo.category.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.category.entity.Category;

public class BeanCategoryRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Category> findAll() {
		String sql = "select * from categories order by id";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
	}

}
