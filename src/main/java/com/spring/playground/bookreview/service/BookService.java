package com.spring.playground.bookreview.service;

import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.domain.Member;
import com.spring.playground.bookreview.repository.BookRepository;
import com.spring.playground.bookreview.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Long save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByAll() {
        return bookRepository.findByAll();
    }

}
