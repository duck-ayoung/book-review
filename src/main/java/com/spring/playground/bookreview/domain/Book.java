package com.spring.playground.bookreview.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Book {

    private Long id;
    private String isbn;
    private String title;
    private String author;

    public Book() {
    }

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
}
