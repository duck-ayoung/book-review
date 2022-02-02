package com.spring.playground.bookreview.web.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class MemberForm {

    @NotBlank
    private String loginId;

    @NotBlank
    private String nickName;

    @NotBlank
    private String password;

    public MemberForm() {
    }

    public MemberForm(String loginId, String nickName, String password) {
        this.loginId = loginId;
        this.nickName = nickName;
        this.password = password;
    }
}
