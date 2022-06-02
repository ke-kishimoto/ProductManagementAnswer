package com.example.demo.controller;

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

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Product;
import com.example.demo.form.LoginForm;
import com.example.demo.form.ProductForm;

@Controller
public class IndexController {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = {"/", "/index"})
	public String index(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		return "index";
	}
	
	@RequestMapping(value="/logout")
	public String logout(@ModelAttribute("loginForm") LoginForm loginForm) {
		// TODO セッション破棄
		return "index";
	}
	
	@RequestMapping(value = "/menu")
	public String menu(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "/index";
		}
		var user = userDao.login(loginForm.getLoginId(), loginForm.getPassword());
		if (user == null) {
			model.addAttribute("errorMsg", "IDまたはパスワードが不正です。");
			return "/index";
		}
		var list = productDao.find("");
		model.addAttribute("productList", list);
		return "menu";
	}
	
	@RequestMapping(value = "/search")
	public String search(@RequestParam("keyword") String keyword ,Model model) {
		var list = productDao.find(keyword);
		model.addAttribute("productList", list);
		return "menu";
	}
	
	@GetMapping("/insert")
	public String insert(Model model) {
		var categoryList = categoryDao.findAll();
		model.addAttribute("categoryList", categoryList);
		return "insert";
	}
	
	@PostMapping("/insert") 
	public String insertProduct(@Validated @ModelAttribute("productForm") ProductForm pForm, BindingResult bindingResult ,Model model) {
		if(bindingResult.hasErrors()) {
			return "/insert";
		}
		var product = new Product();
		product.setName(pForm.getName());
		product.setPrice(pForm.getPrice());
		product.setProductId(pForm.getProductId());
		product.setCategoryId(pForm.getCategoryId());
		product.setDescription(pForm.getDescription());
		productDao.insert(product);
		var list = productDao.find("");
		model.addAttribute("productList", list);
		return "menu";
	}
	
	@RequestMapping(value = "/update", params = "update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute("productForm") ProductForm pForm, BindingResult bindingResult ,Model model) {
		if(bindingResult.hasErrors()) {
			return "/insert";
		}
//		System.out.println("update");
		var product = new Product();
		product.setId(pForm.getId());
		product.setName(pForm.getName());
		product.setPrice(pForm.getPrice());
		product.setProductId(pForm.getProductId());
		product.setCategoryId(pForm.getCategoryId());
		product.setDescription(pForm.getDescription());
		var count = productDao.update(product);
		var list = productDao.find("");
//		System.out.println(count);
		model.addAttribute("productList", list);
		return "menu";
	}
	
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		var product = productDao.findById(id);
		model.addAttribute("product", product);
		return "detail";
	}
	
	@RequestMapping(value = "/update", params = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") int id, Model model) {
		productDao.delete(id);
		var list = productDao.find("");
		model.addAttribute("productList", list);
		return "menu";
	}
	
	@GetMapping("/updateInput/{id}")
	public String updateInput(@PathVariable("id") int id, Model model) {
		var product = productDao.findById(id);
		model.addAttribute("product", product);
		return "updateInput";
	}
	
}
