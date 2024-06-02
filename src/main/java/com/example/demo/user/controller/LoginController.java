package com.example.demo.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring Securityに任せる場合
 */

@Controller
public class LoginController {

    /*
     * トップページへの遷移
     */
    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

}
