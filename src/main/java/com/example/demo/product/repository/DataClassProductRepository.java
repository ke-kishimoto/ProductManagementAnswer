package com.example.demo.product.repository;

import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.record.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataClassProductRepository implements ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<ProductRecord> find(String keyword) {
		
		String sql = """
				select p.id
				, p.product_code
				, p.name
				, c.id category_id
				, c.name category_name
				, p.price
				, p.image_path
				, p.description
				, p.created_at
				, p.updated_at
				from products p
				join categories c
				on p.category_id = c.id
				where p.name || c.name like '%' || :keyword || '%'
				order by p.id
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("keyword", keyword);
		return jdbcTemplate.query(sql, param,  new DataClassRowMapper<>(ProductRecord.class));
	}
	
	public ProductRecord findById(int id) {
		String sql = "select p.*, c.name AS category_name from products p join categories c on p.category_id = c.id where p.id = :id";
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query(sql, param, new DataClassRowMapper<>(ProductRecord.class));
        return list.isEmpty() ? null : list.getFirst();
	}
	
	public int insert(ProductRecord p) {
		String sql = """
				insert into products (product_code, name, price, category_id, created_at)
				values (:product_code, :name, :price, :category_id, now())
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("product_code", p.productCode());
        param.addValue("name", p.name());
        param.addValue("price", p.price());
        param.addValue("category_id", p.categoryId());
        
        return jdbcTemplate.update(sql, param);
	}
	
	public int delete(int id) {
		String sql = "delete from products where id = :id";
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        return jdbcTemplate.update(sql, param);
	}
	
	public int update(ProductRecord p) {
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
		param.addValue("id", p.id());
        param.addValue("product_code", p.productCode());
        param.addValue("name", p.name());
        param.addValue("price", p.price());
        param.addValue("category_id", p.categoryId());
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
        return list.isEmpty() ? null : list.getFirst();
	}
}
