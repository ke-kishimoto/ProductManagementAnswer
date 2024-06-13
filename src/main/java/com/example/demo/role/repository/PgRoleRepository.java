package com.example.demo.role.repository;

import com.example.demo.role.repository.record.RoleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PgRoleRepository implements RoleRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public RoleRecord findById(Integer id) {
        var sql = """
                select * from roles
                where id = :id
                """;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query(sql, param, new DataClassRowMapper<>(RoleRecord.class));
        return list.isEmpty() ? null : list.getFirst();
    }
}
