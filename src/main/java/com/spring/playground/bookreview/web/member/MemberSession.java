package com.spring.playground.bookreview.web.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberSession {

    private String loginId;
    private String nickName;

}
