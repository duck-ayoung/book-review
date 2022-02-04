package com.spring.playground.bookreview.web.login;

import com.spring.playground.bookreview.domain.Member;
import com.spring.playground.bookreview.service.LoginService;
import com.spring.playground.bookreview.web.common.SessionConst;
import com.spring.playground.bookreview.web.member.MemberSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request) {

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디와 비밀번호 불일치");
        }

        if(bindingResult.hasErrors()) {
            log.info("로그인 실패 errors = {}", bindingResult);
            return "redirect:/";
        }

        log.info("로그인 성공");

        HttpSession session = request.getSession();
        MemberSession memberSession = new MemberSession(loginMember.getLoginId(), loginMember.getNickName());
        session.setAttribute(SessionConst.LOGIN_MEMBER, memberSession);

        return "redirect:/";
    }

}
