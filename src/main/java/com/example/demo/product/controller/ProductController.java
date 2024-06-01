package com.example.demo.product.controller;


import com.example.demo.category.entity.Category;
import com.example.demo.category.service.CategoryService;
import com.example.demo.category.view.CategorySelectView;
import com.example.demo.category.vo.CategoryId;
import com.example.demo.category.vo.CategoryName;
import com.example.demo.product.controller.form.ProductInsertForm;
import com.example.demo.product.controller.view.ProductDetailView;
import com.example.demo.product.controller.view.ProductListView;
import com.example.demo.product.service.ProductService;
import com.example.demo.product.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.product.entity.Product;
import com.example.demo.product.controller.form.ProductUpdateForm;

import java.util.List;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	/*
	 * メニュー画面への遷移
	 */
	@RequestMapping(value = "/menu")
	public String menu(@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		var productViewList = productService.find(keyword).stream().map(product -> {
			return new ProductListView(
					product.id().value(),
					product.productCode().value(),
					product.name().value(),
					product.price().value(),
					product.category().name().value()
			);
		}).toList();
		model.addAttribute("productList", productViewList);
		return "menu";
	}
	
	/*
	 * 新規登録画面への遷移
	 */
	@GetMapping("/insert")
	public String insert(@ModelAttribute("productForm") ProductInsertForm insertForm, Model model) {
		model.addAttribute("categoryList", this.getCategoryList());
		return "insert";
	}

	/*
	 * 新規登録時
	 */
	@PostMapping("/insert") 
	public String insertProduct(@Validated @ModelAttribute("productForm") ProductInsertForm insertForm, BindingResult bindingResult , Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("categoryList", this.getCategoryList());
			return "/insert";
		}
		// 存在チェック
		var product = productService.findByProductCode(insertForm.getProductCode());
		if(product != null) {
			model.addAttribute("errorMsg", "商品コードは既に使用されています。");
			model.addAttribute("categoryList", this.getCategoryList());
			return "/insert";
		}

		productService.insert(insertForm.toProduct());

		return "/success";
	}
	
	/*
	 * 更新時
	 */
	@RequestMapping(value = "/update", params = "update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute("productForm") ProductUpdateForm updateForm, BindingResult bindingResult , Model model) {
		if(bindingResult.hasErrors()) {
			return "/updateInput";
		}
		// 存在チェック
		var product = productService.findByProductCode(updateForm.getProductCode());
		if(product != null && product.id().value() == updateForm.getId()) {
			model.addAttribute("errorMsg", "商品コードは既に使用されています。");
			model.addAttribute("categoryList", this.getCategoryList());
			return "/updateInput";
		}

		productService.update(updateForm.toProduct());

		return "/success";
	}
	
	/*
	 * 詳細画面への遷移
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		var product = productService.findById(id);
		var productDetail = new ProductDetailView(
			product.id().value(),
			product.productCode().value(),
			product.name().value(),
			product.category().id().value(),
			product.price().value(),
			product.description().value(),
			""
		);
		model.addAttribute("product", productDetail);
		return "detail";
	}

	/*
	 * 削除時
	 */
	@RequestMapping(value = "/update", params = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") int id, Model model) {
		productService.delete(id);
		return "/success";
	}
	
	/*
	 * 更新画面への遷移
	 */
	@GetMapping("/updateInput/{id}")
	public String updateInput(@ModelAttribute("productForm") ProductUpdateForm updateForm, @PathVariable("id") int id, Model model) {
		var product = productService.findById(id);
		updateForm.setId(product.id().value());
		updateForm.setName(product.name().value());
		updateForm.setPrice(product.price().value());
		updateForm.setProductCode(product.productCode().value());
		updateForm.setCategoryId(product.category().id().value());
		updateForm.setDescription(product.description().value());

		model.addAttribute("categoryList", this.getCategoryList());
		
		return "updateInput";
	}

	private List<CategorySelectView> getCategoryList() {
		return categoryService.findAll().stream().map(category -> {
			return new CategorySelectView(
					category.id().value(),
					category.name().value()
			);
		}).toList();
	}
	
}
