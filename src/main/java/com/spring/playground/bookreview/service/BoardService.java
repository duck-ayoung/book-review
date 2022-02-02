package com.spring.playground.bookreview.service;

import com.spring.playground.bookreview.domain.Board;
import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.domain.Member;
import com.spring.playground.bookreview.repository.BoardRepository;
import com.spring.playground.bookreview.repository.BookRepository;
import com.spring.playground.bookreview.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public Long save(String title, String content, Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId);
        Book book = bookRepository.findById(bookId);
        Board board = Board.createBoard(member, book, title, content);
        return board.getId();
    }

    public void updateBoard(Long boardId, String newTile, String newContent) {
        boardRepository.updateBoard(boardId, newTile, newContent);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findByBook(Book book) {
        return boardRepository.findByBook(book);
    }

    public List<Board> findByMember(Member member) {
        return boardRepository.findByMember(member);
    }

    public List<Board> findByTitle(String title) {
        return boardRepository.findByTitle(title);
    }

    public List<Board> findByAll() {
        return boardRepository.findByAll();
    }
}
