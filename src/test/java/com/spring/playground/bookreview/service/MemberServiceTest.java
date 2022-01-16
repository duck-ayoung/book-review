package com.spring.playground.bookreview.service;

import com.spring.playground.bookreview.domain.Member;
import com.spring.playground.bookreview.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member("sally", "1234");

        //when
        Long memberId = memberService.join(member);

        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findById(memberId));
    }

    @Test
    void joinSameNickName() {
        //given
        Member member1 = new Member("sally", "1234");
        Member member2 = new Member("sally", "1234");

        //when
        Long memberId1 = memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}