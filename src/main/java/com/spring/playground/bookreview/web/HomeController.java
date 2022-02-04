package com.spring.playground.bookreview.web;

import com.spring.playground.bookreview.web.common.SessionConst;
import com.spring.playground.bookreview.web.login.LoginForm;
import com.spring.playground.bookreview.web.member.MemberSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(@ModelAttribute LoginForm loginForm,
                       @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberSession memberSession,
                       Model model) {

        if(memberSession == null) {
            return "home";
        }

        model.addAttribute("memberSession", memberSession);

        return "loginHome";
    }
}
