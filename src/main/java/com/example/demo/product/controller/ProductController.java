package com.example.demo.product.controller;


import com.example.demo.category.entity.Category;
import com.example.demo.category.service.CategoryService;
import com.example.demo.product.service.ProductService;
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
import com.example.demo.product.form.ProductForm;

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
	public String menu(Model model) {
		model.addAttribute("productList", productService.find(""));
		return "menu";
	}
	
	/*
	 * メニュー画面2への遷移
	 */
	@RequestMapping(value = "/menu2")
	public String menu2(Model model) {
		return "menu2";
	}
	
	/*
	 * 検索ボタン押下時
	 */
	@RequestMapping(value = "/search")
	public String search(@RequestParam("keyword") String keyword ,Model model) {
		model.addAttribute("productList", productService.find(keyword));
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
	 * 新規登録画面2への遷移
	 */
	@GetMapping("/insert2")
	public String insert2(@ModelAttribute("productForm") ProductForm pForm, Model model) {
		return "insert2";
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
		var count = productService.insert(product);
		this.setMsg(model, "登録", count);
		
		model.addAttribute("productList", productService.find(""));
		
		return "menu";
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

		var count = productService.update(product);
		this.setMsg(model, "更新", count);
		
		model.addAttribute("productList", productService.find(""));
		
		return "/menu";
	}
	
	/*
	 * 詳細画面への遷移
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "detail";
	}
	
	/*
	 * 詳細画面2への遷移
	 */
	@GetMapping("/detail2")
	public String detail2() {
		return "detail2";
	}
	
	/*
	 * 削除時
	 */
	@RequestMapping(value = "/update", params = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") int id, Model model) {
		var count = productService.delete(id);
		this.setMsg(model, "削除", count);
		model.addAttribute("productList", productService.find(""));
		return "menu";
	}
	
	/*
	 * 更新画面への遷移
	 */
	@GetMapping("/updateInput/{id}")
	public String updateInput(@ModelAttribute("productForm") ProductForm pForm, @PathVariable("id") int id, Model model) {
		var product = productService.findById(id);
		pForm.setId(product.id());
		pForm.setName(product.name());
		pForm.setPrice(product.price());
		pForm.setProductCode(product.productCode());
		pForm.setCategoryId(product.category().id());
		pForm.setDescription(product.description());

		model.addAttribute("categoryList", categoryService.findAll());
		
		return "updateInput";
	}
	
	private Product FormToProduct(ProductForm pForm) {
		return new Product(
				pForm.getId(),
				pForm.getProductCode(),
				pForm.getName(),
				pForm.getPrice(),
				pForm.getDescription(),
				new Category(pForm.getCategoryId(), "")
		);
	}
	
	private void setMsg(Model model, String mode, int count) {
		if(count == 0) {
			model.addAttribute("msg", mode + "に失敗しました。");
		} else {
			model.addAttribute("msg", mode + "に成功しました。");
		}
	}
	
}