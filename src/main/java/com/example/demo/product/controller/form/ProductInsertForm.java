package com.example.demo.product.controller.form;


import com.example.demo.category.entity.Category;
import com.example.demo.category.vo.CategoryId;
import com.example.demo.category.vo.CategoryName;
import com.example.demo.product.entity.Product;
import com.example.demo.product.vo.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductInsertForm {
	
	@NotBlank
	private String productCode;
	@NotBlank
	private String name;
	@NotNull
	private Integer price;
	@NotNull
	private Integer categoryId;
	private String description;

	public Product toProduct() {
		return new Product(
				new ProductId(0),
				new ProductCode(this.getProductCode()),
				new ProductName(this.getName()),
				new Price(this.getPrice()),
				new Description(this.getDescription()),
				new Category(
						new CategoryId(this.getCategoryId()),
						new CategoryName("")
				)
		);
	}
}
