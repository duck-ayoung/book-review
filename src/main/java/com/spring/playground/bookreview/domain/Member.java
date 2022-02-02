package com.spring.playground.bookreview.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;
    private String loginId;
    private String nickName;
    private String password;

    public Member() {
    }

    public Member(String loginId, String nickName, String password) {
        this.loginId = loginId;
        this.nickName = nickName;
        this.password = password;
    }

}
