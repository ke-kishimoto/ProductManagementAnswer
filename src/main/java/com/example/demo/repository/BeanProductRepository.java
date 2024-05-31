package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public class BeanProductRepository implements ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Product> find(String keyword) {
		
		String sql = """
				select p.id
				, p.product_code
				, p.name
				, p.price
				, c.id category_id
				, c.name category_name
				from products p
				join categories c
				on p.category_id = c.id
				where p.name || c.name like '%' || :keyword || '%'
				order by p.id
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("keyword", keyword);
		return jdbcTemplate.query(sql, param,  new BeanPropertyRowMapper<>(Product.class));
	}
	
	public Product findById(int id) {
		String sql = "select * from products where id = :id";
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(Product.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public int insert(Product p) {
		String sql = """
				insert into products (product_code, name, price, category_id, created_at)
				values (:product_code, :name, :price, :category_id, now())
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("product_code", p.getProductCode());
        param.addValue("name", p.getName());
        param.addValue("price", p.getPrice());
        param.addValue("category_id", p.getCategory().getId());
        
        return jdbcTemplate.update(sql, param);
	}
	
	public int delete(int id) {
		String sql = "delete from products where id = :id";
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        return jdbcTemplate.update(sql, param);
	}
	
	public int update(Product p) {
		String sql = """
				update products
				  set product_code = :product_code
				  , name = :name
				  , price = :price
				  , category_id = :category_id
				  , updated_at = now()
				where id = :id
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", p.getId());
        param.addValue("product_code", p.getProductCode());
        param.addValue("name", p.getName());
        param.addValue("price", p.getPrice());
        param.addValue("category_id", p.getCategory().getId());
        
        return jdbcTemplate.update(sql, param);
		
	}
	
	public Product findByProductCode(String productCode, int id) {
		String sql = "select * from products where product_code = :product_code ";
		if (id > 0) {
			sql += "and id <> :id";
		}
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("product_code", productCode);
		param.addValue("id", id);
		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(Product.class));
        return list.isEmpty() ? null : list.get(0);
	}
}
