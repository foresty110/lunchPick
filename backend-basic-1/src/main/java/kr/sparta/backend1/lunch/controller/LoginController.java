package kr.sparta.backend1.lunch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // templates/login.html
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";  // templates/home.html
    }

    @GetMapping("/sign")
    public String signPage() {
        return "sign";  // templates/home.html
    }

}