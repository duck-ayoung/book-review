package com.spring.playground.bookreview.service;

import com.spring.playground.bookreview.domain.Board;
import com.spring.playground.bookreview.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Long save(String title, String content, Long memberId, Long bookId) {
        Board board = Board.createBoard(memberId, bookId, title, content);
        return board.getId();
    }

    public void updateBoard(Long boardId, String newTile, String newContent) {
        boardRepository.updateBoard(boardId, newTile, newContent);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findByBook(Long bookId) {
        return boardRepository.findByBook(bookId);
    }

    public List<Board> findByMember(Long memberId) {
        return boardRepository.findByMember(memberId);
    }

    public List<Board> findByTitle(String title) {
        return boardRepository.findByTitle(title);
    }

    public List<Board> findByAll() {
        return boardRepository.findByAll();
    }
}
