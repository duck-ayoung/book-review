package com.spring.playground.bookreview.repository;

import com.spring.playground.bookreview.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository {

    private static final ConcurrentHashMap<Long, Book> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Long save(Book book) {
        book.setId(++sequence);
        store.put(book.getId(), book);
        return  book.getId();
    }

    public Book findById(Long id) {
        return store.get(id);
    }

    public List<Book> findByTitle(String title) {
        String findTitle = removeSpace(title);
        List<Book> bookList = findByAll();
        List<Book> findList = new ArrayList<>();
        for (Book book : bookList) {
            String bookTitle = removeSpace(book.getTitle());
            if(bookTitle.contains(findTitle)) {
                findList.add(book);
            }
        }
        return findList;
    }

    private String removeSpace(String title) {
        return title.replaceAll("\\s", "");
    }

    public List<Book> findByAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
