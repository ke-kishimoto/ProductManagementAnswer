package com.example.demo.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.demo.user.entity.User;

public class BeanUserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public User login(String loginId, String password) {
		String sql = """
				select * from users
				where login_id = :login_id
				and password = :password
				""";
		
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("login_id", loginId);
        param.addValue("password", password);
        var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(User.class));
        return list.isEmpty() ? null : list.getFirst();
	}
	
}
