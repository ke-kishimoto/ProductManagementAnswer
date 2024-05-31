package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.record.CategoryRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataClassCategoryRepository implements CategoryRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<CategoryRecord> findAll() {
		String sql = "select * from categories order by id";
		return jdbcTemplate.query(sql, new DataClassRowMapper<>(CategoryRecord.class));
	}

}
