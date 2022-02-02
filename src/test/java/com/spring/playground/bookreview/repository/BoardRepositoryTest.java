package com.spring.playground.bookreview.repository;

import com.spring.playground.bookreview.domain.Board;
import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardRepositoryTest {

    BoardRepository boardRepository = new BoardRepository();
    MemberRepository memberRepository = new MemberRepository();
    BookRepository bookRepository = new BookRepository();

    @AfterEach
    void tearDown() {
        boardRepository.clearStore();
        memberRepository.clearStore();
        bookRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("sally", "1234");
        memberRepository.save(member);

        Book book = new Book("111aaa", "sally book", "sally");

        Board board = Board.createBoard(member, book, "sally book is good", "sally book is good");

        //when
        Long boardId = boardRepository.save(board);
        Board findBoard = boardRepository.findById(boardId);

        //then
        assertThat(findBoard).isEqualTo(board);
    }

    @Test
    void updateBoard() {
        //given
        Member member = new Member("sally", "1234");
        memberRepository.save(member);

        Book book = new Book("111aaa", "sally book", "sally");

        Board board = Board.createBoard(member, book, "sally book is good", "sally book is good");
        Long boardId = boardRepository.save(board);

        //when
        Board findBoard = boardRepository.findById(boardId);
        String newTitle = "sally book is good...";
        String newContent = "sally book is very good!!";
        boardRepository.updateBoard(findBoard.getId(), newTitle, newContent);

        //then
        assertThat(findBoard.getTitle()).isEqualTo(newTitle);
        assertThat(findBoard.getContent()).isEqualTo(newContent);
    }

    @Test
    void findByBook() {
        //given
        Member member = new Member("sally", "1234");
        memberRepository.save(member);

        Book book1 = new Book("111aaa", "sally book", "sally");

        Board board1 = Board.createBoard(member, book1, "sally book is good", "sally book is good");
        boardRepository.save(board1);

        Board board2 = Board.createBoard(member, book1, "sally book is good too", "sally book is good");
        boardRepository.save(board2);


        Book book2 = new Book("111aaa", "cally book", "cally");

        Board board3 = Board.createBoard(member, book2, "cally book is good too", "cally book is good");
        boardRepository.save(board3);

        //when
        List<Board> findBoardList = boardRepository.findByBook(book1);

        //then
        assertThat(findBoardList.size()).isEqualTo(2);
        assertThat(findBoardList).contains(board1, board2);
    }

    @Test
    void findByMember() {
        //given
        Member member1 = new Member("sally1", "1234");
        memberRepository.save(member1);

        Book book = new Book("111aaa", "sally book", "sally");

        Board board1 = Board.createBoard(member1, book, "sally book is good", "sally book is good");
        boardRepository.save(board1);

        Board board2 = Board.createBoard(member1, book, "sally book is good too", "sally book is good");
        boardRepository.save(board2);

        Member member2 = new Member("sally2", "1234");
        memberRepository.save(member2);

        Board board3 = Board.createBoard(member2, book, "cally book is good too", "cally book is good");
        boardRepository.save(board3);

        //when
        List<Board> findBoardList = boardRepository.findByMember(member1);

        //then
        assertThat(findBoardList.size()).isEqualTo(2);
        assertThat(findBoardList).contains(board1, board2);
    }

    @Test
    void findByTitle() {
        //given
        Member member = new Member("sally1", "1234");
        memberRepository.save(member);

        Book book = new Book("111aaa", "sally book", "sally");

        Board board1 = Board.createBoard(member, book, "sally book is good", "sally book is good");
        boardRepository.save(board1);

        Board board2 = Board.createBoard(member, book, "sally book is good too", "sally book is good");
        boardRepository.save(board2);

        Board board3 = Board.createBoard(member, book, "book is good too", "sally book is good");
        boardRepository.save(board3);


        //when
        List<Board> findBoardList = boardRepository.findByTitle("sally");

        //then
        assertThat(findBoardList.size()).isEqualTo(2);
        assertThat(findBoardList).contains(board1, board2);
    }

}