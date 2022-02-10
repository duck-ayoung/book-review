package com.spring.playground.bookreview;

import com.spring.playground.bookreview.domain.Board;
import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.domain.Member;
import com.spring.playground.bookreview.repository.BoardRepository;
import com.spring.playground.bookreview.repository.BookRepository;
import com.spring.playground.bookreview.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BoardRepository boardRepository;

    @PostConstruct
    public void init() {
        Member member = new Member("test", "test", "test");
        memberRepository.save(member);

        Book book1 = new Book("book1", "book1", "sally");
        Book book2 = new Book("book2", "book2", "sally");

        bookRepository.save(book1);
        bookRepository.save(book2);

        Board board1 = Board.createBoard(member.getId(), book1.getId(), "sally book is good", "sally book is good");
        boardRepository.save(board1);
    }
}
