package com.spring.playground.bookreview.web.book;

import com.spring.playground.bookreview.domain.Book;
import com.spring.playground.bookreview.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public String bookList(Model model) {
        log.info("bookList");
        List<Book> books = bookRepository.findByAll();
        model.addAttribute("books", books);
        return "/books/bookList";
    }

    @PostMapping
    public String searchBookList(Model model) {
        log.info("bookList");
        List<Book> books = bookRepository.findByAll();
        model.addAttribute("books", books);
        return "/books/bookList";
    }
}
