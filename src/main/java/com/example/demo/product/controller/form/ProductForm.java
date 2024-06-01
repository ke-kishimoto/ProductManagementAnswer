package com.example.demo.product.controller.form;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductForm {
	
	private int id;
	@NotBlank
	private String productCode;
	@NotBlank
	private String name;
	@NotNull
	private int price;
	@NotNull
	private int categoryId;
	private String description;
}
