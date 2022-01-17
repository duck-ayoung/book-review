package com.spring.playground.bookreview.repository;

import com.spring.playground.bookreview.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRepositoryTest {
    
    @Autowired BookRepository bookRepository;
    
    @AfterEach
    void afterEach() {
        bookRepository.clearStore();
    }

    @Test
    void save() {
        //give
        Book book = new Book("111aaa", "sally book", "sally");

        //when
        Long saveId = bookRepository.save(book);
        Book saveBook = bookRepository.findById(saveId);

        //then
        assertThat(saveBook).isEqualTo(book);
    }
    
    @Test
    void findByFullTitle() {
        //give
        Book book1 = new Book("111aaa", "sally book1", "sally");
        Book book2 = new Book("222aaa", "holly book", "holly");

        bookRepository.save(book1);
        bookRepository.save(book2);
        
        //when
        List<Book> bookList = bookRepository.findByTitle("sallybook1");

        //then
        assertThat(bookList.size()).isEqualTo(1);
        assertThat(bookList).contains(book1);
    }
    
    @Test
    void findByPartTitle() {
        //give
        Book book1 = new Book("111aaa", "sally book1", "sally");
        Book book2 = new Book("222aaa", "holly book", "holly");

        bookRepository.save(book1);
        bookRepository.save(book2);

        //when
        List<Book> bookList = bookRepository.findByTitle("book");

        //then
        assertThat(bookList.size()).isEqualTo(2);
        assertThat(bookList).contains(book1, book2);
    }
    
    @Test
    void findByNoneTitle() {
        //give
        Book book1 = new Book("111aaa", "sally book1", "sally");
        Book book2 = new Book("222aaa", "holly book", "holly");

        bookRepository.save(book1);
        bookRepository.save(book2);

        //when
        List<Book> bookList = bookRepository.findByTitle("none");

        //then
        assertThat(bookList.size()).isEqualTo(0);
    }

    @Test
    void findByAll() {
        //give
        Book book1 = new Book("111aaa", "sally book", "sally");
        Book book2 = new Book("222aaa", "sally book2", "sally");
        
        bookRepository.save(book1);
        bookRepository.save(book2);
        
        //when
        List<Book> bookList = bookRepository.findByAll();

        //then
        assertThat(bookList.size()).isEqualTo(2);
        assertThat(bookList).contains(book1, book2);
    }
}