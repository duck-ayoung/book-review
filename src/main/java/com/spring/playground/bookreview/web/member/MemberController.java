package com.spring.playground.bookreview.web.member;

import com.spring.playground.bookreview.domain.Member;
import com.spring.playground.bookreview.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String add(@ModelAttribute MemberForm memberForm) {
        return "/members/addMemberForm";
    }

    @PostMapping("/add")
    public String join(@Validated @ModelAttribute MemberForm memberForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("join fail errors={}", bindingResult);
            return "/members/addMemberForm";
        }

        Member member = new Member(memberForm.getLoginId(), memberForm.getNickName(), memberForm.getPassword());
        memberService.join(member);

        log.info("join success member={}", member.getId() + " " + member.getNickName());
        return "redirect:/";
    }
}
