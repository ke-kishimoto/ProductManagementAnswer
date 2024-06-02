package com.example.demo.user.controller;

import com.example.demo.product.service.ProductService;
import com.example.demo.user.form.LoginForm;
import com.example.demo.user.service.RegularUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* Spring Securityを使用しない場合のログイン処理用
*/

//@Controller
public class UserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RegularUserService userService;

    @Autowired
    HttpSession session;

    /*
     * トップページへの遷移
     */
    @GetMapping(value = {"/", "/login"})
    public String index() {
        return "login";
    }

    /*
     * ログアウト
     */
    @RequestMapping(value="/logout")
    public String logout(@ModelAttribute("loginForm") LoginForm loginForm) {
        session.invalidate();
        return "redirect:/index";
    }

    /*
     * ログイン
     */
    @PostMapping(value="/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "/login";
        }
        var user = userService.login(loginForm.getLoginId(), loginForm.getPassword());
        if (user == null) {
            model.addAttribute("errorMsg", "IDまたはパスワードが不正です。");
            return "/login";
        }
        session.setAttribute("user", user);
        return "redirect:/menu";
    }
}
