package com.example.demo.user.controller;

import com.example.demo.product.service.ProductService;
import com.example.demo.user.form.LoginForm;
import com.example.demo.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session;

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
        session.invalidate();
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
        var user = userService.login(loginForm.getLoginId(), loginForm.getPassword());
        if (user == null) {
            model.addAttribute("errorMsg", "IDまたはパスワードが不正です。");
            return "/index";
        }
        session.setAttribute("user", user);
        model.addAttribute("productList", productService.find(""));
        return "menu";
    }
}
