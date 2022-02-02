package com.spring.playground.bookreview.repository;

import com.spring.playground.bookreview.domain.Board;
import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BoardRepository {

    private static final ConcurrentHashMap<Long, Board> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Long save(Board board) {
        board.setId(++sequence);
        store.put(board.getId(), board);
        return  board.getId();
    }

    public void updateBoard(Long boardId, String newTile, String newContent) {
        Board board = findById(boardId);
        board.updateBoard(newTile, newContent);
    }

    public Board findById(Long id) {
        return store.get(id);
    }

    public List<Board> findByBook(Book book) {
        List<Board> boardList = findByAll();
        List<Board> findList = new ArrayList<>();
        for (Board board : boardList) {
            if(board.getBook() == book) {
                findList.add(board);
            }
        }
        return findList;
    }

    public List<Board> findByMember(Member member) {
        List<Board> boardList = findByAll();
        List<Board> findList = new ArrayList<>();
        for (Board board : boardList) {
            if(board.getMember() == member) {
                findList.add(board);
            }
        }
        return findList;
    }

    public List<Board> findByTitle(String title) {
        String findTitle = removeSpace(title);
        List<Board> boardList = findByAll();
        List<Board> findList = new ArrayList<>();
        for (Board board : boardList) {
            String bookTitle = removeSpace(board.getTitle());
            if(bookTitle.contains(findTitle)) {
                findList.add(board);
            }
        }
        return findList;
    }

    public List<Board> findByAll() {
        return new ArrayList<>(store.values());
    }

    private String removeSpace(String title) {
        return title.replaceAll("\\s", "");
    }


    public void clearStore() {
        store.clear();
    }
}
