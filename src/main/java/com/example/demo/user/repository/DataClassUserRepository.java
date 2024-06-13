package com.example.demo.user.repository;

import com.example.demo.user.repository.record.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataClassUserRepository implements UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public UserRecord login(String loginId, String password) {
		String sql = """
				select * from users
				where login_id = :login_id
				and password = :password
				""";
		
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("login_id", loginId);
        param.addValue("password", password);
        var list = jdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserRecord.class));
        return list.isEmpty() ? null : list.getFirst();
	}

	@Override
	public UserRecord findByLoginId(String loginId) {
		String sql = """
				select * from users
				where login_id = :login_id
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_id", loginId);
		var list = jdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserRecord.class));
		return list.isEmpty() ? null : list.getFirst();
	}

}
