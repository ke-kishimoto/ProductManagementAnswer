package com.example.demo.product.controller;


import com.example.demo.category.entity.Category;
import com.example.demo.category.service.CategoryService;
import com.example.demo.category.view.CategorySelectView;
import com.example.demo.category.vo.CategoryId;
import com.example.demo.category.vo.CategoryName;
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
import com.example.demo.product.controller.form.ProductForm;

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
	public String insert(@ModelAttribute("productForm") ProductForm pForm, Model model) {
		var categoryList = categoryService.findAll();
		model.addAttribute("categoryList", categoryList);
		return "insert";
	}

	/*
	 * 新規登録時
	 */
	@PostMapping("/insert") 
	public String insertProduct(@Validated @ModelAttribute("productForm") ProductForm pForm, BindingResult bindingResult ,Model model) {
		if(bindingResult.hasErrors()) {
			return "/insert";
		}
		// 存在チェック
		var product = productService.findByProductCode(pForm.getProductCode(), -1);
		if(product != null) {
			model.addAttribute("errorMsg", "商品コードは既に使用されています。");
			return "/insert";
		}
		
		product = this.FormToProduct(pForm);
		productService.insert(product);

		return "/success";
	}
	
	/*
	 * 更新時
	 */
	@RequestMapping(value = "/update", params = "update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute("productForm") ProductForm pForm, BindingResult bindingResult ,Model model) {
		if(bindingResult.hasErrors()) {
			return "/updateInput";
		}
		// 存在チェック
		var product = productService.findByProductCode(pForm.getProductCode(), pForm.getId());
		if(product != null) {
			model.addAttribute("errorMsg", "商品コードは既に使用されています。");
			model.addAttribute("categoryList", categoryService.findAll());
			return "/updateInput";
		}
		product = this.FormToProduct(pForm);

		productService.update(product);

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
	public String updateInput(@ModelAttribute("productForm") ProductForm pForm, @PathVariable("id") int id, Model model) {
		var product = productService.findById(id);
		pForm.setId(product.id().value());
		pForm.setName(product.name().value());
		pForm.setPrice(product.price().value());
		pForm.setProductCode(product.productCode().value());
		pForm.setCategoryId(product.category().id().value());
		pForm.setDescription(product.description().value());

		var categoryList = categoryService.findAll().stream().map(category -> {
			return new CategorySelectView(
					category.id().value(),
					category.name().value()
			);
		}).toList();
		model.addAttribute("categoryList", categoryList);
		
		return "updateInput";
	}
	
	private Product FormToProduct(ProductForm pForm) {
		return new Product(
				new ProductId(pForm.getId()),
				new ProductCode(pForm.getProductCode()),
				new ProductName(pForm.getName()),
				new Price(pForm.getPrice()),
				new Description(pForm.getDescription()),
				new Category(
						new CategoryId(pForm.getCategoryId()),
						new CategoryName("")
				)
		);
	}
	
}
