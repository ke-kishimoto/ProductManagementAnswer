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
	
	/*  
	 * トップページへの遷移
	 */
	@RequestMapping(value = {"/", "/index"})
	public String index(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		return "index";
	}
	
	/*
	 * ログアウト
	 */
	@RequestMapping(value="/logout")
	public String logout(@ModelAttribute("loginForm") LoginForm loginForm) {
		// TODO セッション破棄
		return "index";
	}
	
	/*
	 * ログイン
	 */
	@RequestMapping(value="/login")
	public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
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
	
	/*
	 * メニュー画面への遷移
	 */
	@RequestMapping(value = "/menu")
	public String menu(Model model) {
		var list = productDao.find("");
		model.addAttribute("productList", list);
		return "menu";
	}
	
	/*
	 * 検索ボタン押下時
	 */
	@RequestMapping(value = "/search")
	public String search(@RequestParam("keyword") String keyword ,Model model) {
		var list = productDao.find(keyword);
		model.addAttribute("productList", list);
		return "menu";
	}
	
	/*
	 * 新規登録画面への遷移
	 */
	@GetMapping("/insert")
	public String insert(@ModelAttribute("productForm") ProductForm pForm, Model model) {
		var categoryList = categoryDao.findAll();
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
		var user = productDao.findByProductId(pForm.getProductId(), -1);
		if(user != null) {
			model.addAttribute("errorMsg", "商品IDは既に使用されています。");
			return "/insert";
		}
		
		var product = new Product();
		product.setName(pForm.getName());
		product.setPrice(pForm.getPrice());
		product.setProductId(pForm.getProductId());
		product.setCategoryId(pForm.getCategoryId());
		product.setDescription(pForm.getDescription());
		var count = productDao.insert(product);
		
		if(count == 0) {
			model.addAttribute("msg", "登録に失敗しました。");
		} else {
			model.addAttribute("msg", "登録に成功しました。");
		}
		
		var list = productDao.find("");
		model.addAttribute("productList", list);
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
		var user = productDao.findByProductId(pForm.getProductId(), pForm.getId());
		if(user != null) {
			model.addAttribute("errorMsg", "商品IDは既に使用されています。");
			model.addAttribute("categoryList", categoryDao.findAll());
			return "/updateInput";
		}
		var product = new Product();
		product.setId(pForm.getId());
		product.setName(pForm.getName());
		product.setPrice(pForm.getPrice());
		product.setProductId(pForm.getProductId());
		product.setCategoryId(pForm.getCategoryId());
		product.setDescription(pForm.getDescription());
		var count = productDao.update(product);
		
		if(count == 0) {
			model.addAttribute("msg", "更新に失敗しました。");
		} else {
			model.addAttribute("msg", "更新に成功しました。");
		}
		
		var list = productDao.find("");
		model.addAttribute("productList", list);
		return "/menu";
	}
	
	/*
	 * 詳細画面への遷移
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		var product = productDao.findById(id);
		model.addAttribute("product", product);
		return "detail";
	}
	
	/*
	 * 削除時
	 */
	@RequestMapping(value = "/update", params = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") int id, Model model) {
		var count = productDao.delete(id);
		if(count == 0) {
			model.addAttribute("msg", "削除に失敗しました。");
		} else {
			model.addAttribute("msg", "削除に成功しました。");
		}
		var list = productDao.find("");
		model.addAttribute("productList", list);
		return "menu";
	}
	
	/*
	 * 更新画面への遷移
	 */
	@GetMapping("/updateInput/{id}")
	public String updateInput(@ModelAttribute("productForm") ProductForm pForm, @PathVariable("id") int id, Model model) {
		var product = productDao.findById(id);
//		model.addAttribute("product", product);
		pForm.setId(product.getId());
		pForm.setName(product.getName());
		pForm.setPrice(product.getPrice());
		pForm.setProductId(product.getProductId());
		pForm.setCategoryId(product.getCategory().getId());
		pForm.setDescription(product.getDescription());

		model.addAttribute("categoryList", categoryDao.findAll());
		
		return "updateInput";
	}
	
}
