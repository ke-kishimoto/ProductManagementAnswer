package com.example.demo.product.controller;

import com.example.demo.product.controller.form.ProductUpdateForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductJsController {

    /*
     * メニュー画面2への遷移
     */
    @RequestMapping(value = "/menu2")
    public String menu2(Model model) {
        return "menu2";
    }


    /*
     * 新規登録画面2への遷移
     */
    @GetMapping("/insert2")
    public String insert2(@ModelAttribute("productForm") ProductUpdateForm pForm, Model model) {
        return "insert2";
    }

    /*
     * 詳細画面2への遷移
     */
    @GetMapping("/detail2")
    public String detail2() {
        return "detail2";
    }


}
