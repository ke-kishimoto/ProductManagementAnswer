package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductForm {
	
	private int id;
	@NotBlank
	private String productId;
	@NotBlank
	private String name;
	@NotNull
	private int price;
	@NotNull
	private int categoryId;
	private String description;
}
