package com.spring.playground.bookreview.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.apache.coyote.http11.Constants.a;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository repository = new MemberRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        //give
        Member member = new Member("sally", "1234");

        //when
        Long saveId = repository.save(member);
        Member saveMember = repository.findById(saveId);

        //then
        assertThat(saveMember).isEqualTo(member);
    }

    @Test
    void findByAll() {
        //give
        Member member1 = new Member("sally1", "1234");
        Member member2 = new Member("sally2", "1234");

        repository.save(member1);
        repository.save(member2);

        //when
        List<Member> memberList = repository.findByAll();

        //then
        assertThat(memberList.size()).isEqualTo(2);
        assertThat(memberList).contains(member1, member2);

    }

    @Test
    void update() {
        //give
        Member member1 = new Member("sally1", "1234");

        Long memberId = repository.save(member1);

        //when
        Member updateMember = new Member("sally2", "1234");
        repository.update(memberId, updateMember);

        Member member = repository.findById(memberId);

        //then
        assertThat(member.getNickName()).isEqualTo(updateMember.getNickName());
        assertThat(member.getPassword()).isEqualTo(updateMember.getPassword());
    }
}