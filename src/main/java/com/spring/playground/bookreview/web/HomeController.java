package com.spring.playground.bookreview.web;

import com.spring.playground.bookreview.web.login.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(@ModelAttribute LoginForm loginForm) {
        return "home";
    }
}
