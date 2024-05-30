package com.example.demo.entity;

import lombok.Data;

@Data
public class Product {
	
	private int id;
	private String productCode;
	private String name;
	private int price;
	private String description;
	
	private Category category;
	
	public void setCategoryId(int categoryId) {
		if(this.category == null) {
			this.category = new Category();
		}
		category.setId(categoryId);
	}
	
	public void setCategoryName(String categoryName) {
		if(this.category == null) {
			this.category = new Category();
		}
		category.setName(categoryName);
	}

}
