package com.spring.playground.bookreview.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Board {

    private Long id;
    private String title;
    private String content;

    private Member member;
    private Book book;

    private LocalDateTime modifyTime;

    public Board(String title, String content, Member member, Book book) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.book = book;
    }

    //생성 로직
    public static Board createBoard(Member member, Book book, String title, String content) {
        Board board = new Board(title, content, member, book);
        board.setModifyTime(LocalDateTime.now());
        return  board;
    }

    //수정 로직
    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifyTime = LocalDateTime.now();
    }
}
