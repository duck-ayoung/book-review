package com.spring.playground.bookreview.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;
    private String nickName;
    private String password;

    public Member() {
    }

    public Member(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }
}
