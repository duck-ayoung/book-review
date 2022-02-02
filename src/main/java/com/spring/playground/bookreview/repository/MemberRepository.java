package com.spring.playground.bookreview.repository;

import com.spring.playground.bookreview.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {

    private static final ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Long save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return  member.getId();
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findByAll().stream().filter(member -> member.getLoginId().equals(loginId))
                .findFirst();
    }

    public Member findByNickName(String nickName) {
        List<Member> memberList = findByAll();
        for (Member member : memberList) {
            if(member.getNickName().equals(nickName)) {
                return member;
            }
        }
        return null;
    }

    public List<Member> findByAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long memberId, Member updateMember) {
        Member findMember = findById(memberId);
        findMember.setPassword(updateMember.getPassword());
        findMember.setNickName(updateMember.getNickName());
    }

    public void clearStore() {
        store.clear();
    }
}
