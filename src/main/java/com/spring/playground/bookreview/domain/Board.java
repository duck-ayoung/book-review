package com.spring.playground.bookreview.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Board {

    private Long id;
    private String title;
    private String content;

    private Long memberId;
    private Long bookId;

    private LocalDateTime modifyTime;

    public Board(String title, String content, Long memberId, Long bookId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.bookId = bookId;
    }

    //생성 로직
    public static Board createBoard(Long memberId, Long bookId, String title, String content) {
        Board board = new Board(title, content, memberId, bookId);
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
